(function () {
'use strict';
var app = angular.module('nAutomationApp');

app.controller('inputDataCtrl',
		function($scope, $window, $location, $http,$document) {
	
			$scope.pagenames = [ "login", "checkout", "payment" ];
			$scope.domains = [ "Safeway", "Walmart", "Gap", "Target" ];


			$scope.browsers = [ "Chrome", "Firefox", "Safari", "Explorer" ];
			
            $scope.project = {
            		pageURL:"testingpageurl123",
            		testSuite:[]
                    };
            
			$scope.selectedFileExtension ="html";
			$scope.selectedDName;
			
            $scope.elements = [];

			$scope.saveTestSuites = [];			
			
			console.log(JSON.stringify($scope.saveTestSuites));
		
			$scope.testSuites = {};
			$scope.testSuitsPerm = [];
			$scope.testCases = [];
			$scope.inputTestSuites = [];

	
			
			function createTestSuiteObject(data){
				
				data.forEach(function(testSuite){
					//alert(testSuite);
					if($scope.testSuites[testSuite.testSuiteName]){

						$scope.testSuites[testSuite.testSuiteName].push(testSuite.testCases);
					}else {
						
						$scope.testSuites[testSuite.testSuiteName] = [];
						$scope.testSuites[testSuite.testSuiteName].push(testSuite);
						
					}
					
				});
				
			}
			
			$scope.changeSuite = function(suiteSeleted) {
				$scope.selectedTestSuiteName =suiteSeleted;
				
				

	//			console.log(suiteSeleted);
				$scope.testCases = $scope.testSuites[suiteSeleted];
//				console.log($scope.testCases);
				$scope.inputTestSuites.forEach(function(testSuite){
		//			console.log(testSuite.testSuiteName);
					if(testSuite.testSuiteName == suiteSeleted){
						console.log(11);
						$scope.testCases = testSuite.testCases;
						console.log(testSuite.testCases);
						return;
					}
					
				});
			};

				
			
			$scope.DropDownDomains= function(selectedDName) {
				
				switch (selectedDName) {
		          case selectedDName:
		            $scope.project.domainName =  "http://www.safeway.com";
					$scope.selectedDName =  "http://www.safeway.com";
		            break;

		          default:
		            alert("Selected Domain not supported yet");
		            break;

		          }
			  };
			
			
		    $scope.dropDownChanged = function (selectedPageName) {
				
				switch (selectedPageName) {
		          case "login":
			        $scope.selectedPageName = "ShopStores/OSSO-Login.page";
		            break;

		          default:
		            alert("Selected Page Name not supported yet");
		            break;

		        }
				
		    	//alert("latest");
		        $scope.selectedPageName = selectedPageName;
		        //var valData = $scope.selectedDName + $scope.selectedPageName + "." + $scope.selectedFileExtension;
		        var valData = $scope.selectedDName +  "/" + $scope.selectedPageName;

		       // var valData1 ="http://www.safeway.com/ShopStores/OSSO-Login.page";
		        console.log("may 4th valData encodeURIComponent(valData)= " + encodeURIComponent(valData));
		 		        
		        
		        $http.get('./getElements?input='+
		                encodeURIComponent(valData)).success(function(data, status) {
		                  //alert("i am" + status);
		                  $scope.elements = data;
		                })
		                .error(function(data, status) {
		                  //alert(status);
		                 // alert( "failure message: " + JSON.stringify(data));
		                });
		        };
		        
			$scope.testcase;
			
			$scope.changeCase = function(selectedCase) {
				$scope.selectedCase =selectedCase;
	/*
				var tcase = {
						"testCaseName":selectedCase,
						"testCaseDesc": "fooCaseDecription"
				} ;
								
				console.log ( " test line 45 " + $scope.project.testSuite[0] );
				
				if(!$scope.project.testSuite[0].testCases) {
					$scope.project.testSuite[0].testCases = [];
				}

				$scope.project.testSuite[0].testCases.push(tcase);			
		*/		
			};
			
			/*$scope.changeSuite = function(selectedSuite) {				

				var t = {
						"testSuiteName":selectedSuite,
						"testCases": $scope.testcase
				} ;
				
				
				$scope.project.testSuite.push(t);
				$scope.testCases = $scope.testSuitsPerm[selectedSuite];
			};
*/			
			var path = $location.absUrl().substr(0,
					$location.absUrl().lastIndexOf("/"));

			$scope.dropDownChnaged = function() {
				//alert(url.name);
				var valData = $scope.selectedDName + $scope.selectedPageName
						+ "." + $scope.selectedFileExtension;
				
				//$scope.selectedPage=$scope.selectedPage;
				$http.get('./getElements?input='
								+ encodeURIComponent(valData)).success(
						function(data, status) {
							//alert("i am" + status);

							$scope.elements = data;
						}).error(function(data, status) {
					console.log("failure message: " + JSON.stringify(data));
				});
			};

			/*
			$scope.project = {  
					 testSuite : [ 
					               { 
					            	 testSuiteId:"", 
					            	 testSuiteName:"", 
					            	 testSuiteDesc:"",
					            	 testCases:[
					            	              {
					            	            	  testCaseId:"",
					            	            	  testCaseName:"",
					            	            	  testCaseDesc:"",
					            	            	  testSteps:[
					            	            	               {
					            	            	            	   "testStepId": "",
					            	            	            	   "testStepName":"",
					            	            	            	   "expected":"",
					            	            	            	   "actual":"",
					            	            	            	   "status":""                                                                                                                                        
					            	            	               	}
					            	            	               ], 
		                                pageElement:[
		                                               {
		                                            	   pageElementId:"",
		                                            	   pageElementName:"",
		                                            	   pageElementDesc:""
		                                               }
		                                               ]                                  
					            	              }
					            	             ]	
					               }
					             ],     
		   pageURL: "",
		   pageName :  "",
		   brwType : "",
		   domainName : ""    
		};
			
			*/

			$scope.selectedTestSuiteName ={};
			$scope.selectedCase ={};
			$scope.selectPageElement =[];
			$scope.changePageElement= function(selectPageElement){
				console.log('selectPageElement = '+JSON.stringify(selectPageElement));
				$scope.selectPageElement =selectPageElement;
			};
			
			
			$scope.postHttpJSON = function() {
				
				var pageElement =[];
				console.log('postHttpJSON selectPageElement = '+JSON.stringify($scope.selectPageElement));
				
				  
				
				if($scope.selectPageElement){
					$scope.selectPageElement.forEach(function(pg){
						
						console.log(' document.querySelector(pg.pageElementId) : '+ document.querySelector(pg.pageElementId));
						var queryResult = $document[0].getElementById(pg.pageElementId);
						  
						if(queryResult)
						pageElement.push({
				        	 
				        	 'pageElementId' : pg.pageElementId,
				        	 'pageElementValue' : queryResult.value,
				        	 'pageElementType' : $scope.pageElementType,
				       		
				           });	
					});
					
				}
				//var y =document.querySelector('#id')
			    //angular.element(count).html(y);
				var ts= {
						'testSuiteName': $scope.selectedTestSuiteName,
						'testCases':[
						           {
						        	 'testCaseName' : $scope.selectedCase,
						        	 'pageElement' : pageElement
						           }]
						
				};
				$scope.project.testSuite.push(ts);
                console.log('postHttpJSON $scope.project = '+JSON.stringify($scope.project));

                $http.post('projects/', $scope.project )
                .success(function (data, status, headers)
                {
                 console.log('Success'+JSON.stringify(data));
                 $scope.project.testSuite = [];
                })
                .error(function (data, status, headers)
                {
                 console.log('Failure');
                 $scope.project.testSuite = [];
                });
                
/*				
				$http({
					url : "projects",
					dataType : 'json',
					method : 'POST',
					data : $scope.postObject,
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					$scope.response = response;
                    console.log('Success'+JSON.stringify(response));
				}).error(function(error) {
					$scope.error = error;
					
                    console.log('Failure');
				});
				
				*/
                
			};

			$scope.getAllProjectsHttpJSONByName = function() {
				$http({
					url : 'http://localhost:8080/projects/',
					dataType : 'json',
					method : 'GET',
					data : postObject,
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					$scope.response = response;
				}).error(function(error) {
					$scope.error = error;
				});
			};

			$scope.getTestSuiteHttpJSONByProjects = function() {
				$http({
					url : 'http://localhost:8080/testsuites',
					dataType : 'json',
					method : 'GET',
					data : postObject,
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					$scope.response = response;
				}).error(function(error) {
					$scope.error = error;
				});
			};

			$scope.getTestCasesHttpJSONByTestSuite = function() {
				$http({
					url : 'http://localhost:8080/pgElements',
					dataType : 'json',
					method : 'GET',
					data : postObject,
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					$scope.response = response;
				}).error(function(error) {
					$scope.error = error;
				});
			};

			$scope.getPageElementHttpJSONByTestCases = function() {
				$http({
					url : 'http://localhost:8080/pgElements',
					dataType : 'json',
					method : 'GET',
					data : postObject,
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					$scope.response = response;
				}).error(function(error) {
					$scope.error = error;
				});
			};

			$scope.buildInputData = function(){
				$http.get('./testsuites').success(
						function(data, status) {
							$scope.inputTestSuites = data;
						//	alert("getting input");
							
							createTestSuiteObject(data);
						    console.log(JSON.stringify($scope.inputTestSuites));
						}).error(function(data, status) {
				});

			};			
			
});



})();
