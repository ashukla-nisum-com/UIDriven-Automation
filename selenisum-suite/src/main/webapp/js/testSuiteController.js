(function () {
'use strict';
var app = angular.module('nAutomationApp');

app.controller('testSuiteCtrl', [ '$scope', '$http', '$window', '$location',
		function($scope, $http, $window, $location) {
		$scope.disablePageName = false;
		$scope.pagenames = [ "ShopStores/OSSO-Login.page", "login", "checkout", "payment" ];
			$scope.testSuite = {
					testSuiteName : "",
					testCases : [],
					pageName :""
			};
			

			var counter = 0;
			$scope.testsuiteelemnt = [ {
				'id' : 'counter',
				'question' : 'TEST-SUITE',
				'answer' : '',
				'inline' : 'true'
			} ];

			$scope.newItem = function($event) {
				counter++;
				$scope.testsuiteelemnt.push({
					'id' : 'counter',
					'question' : 'TEST-SUITE',
					'answer' : '',
					'inline' : 'true'
				});
				$event.preventDefault();
			}

			$scope.setSuiteName = function($event) {
				$scope.hideTxtBox = false;
				//$event.preventDefault();
			}

			$scope.makeSuiteNameStatic = function() {
				if(($scope.testSuite.testSuiteName === undefined || $scope.testSuite.testSuiteName === "")) {
					$scope.hideTxtBox = false;
				} else {
					$scope.hideTxtBox = true;
				}
				//$event.preventDefault();
			}
			
			$scope.selectPageName = function() {
				$scope.disablePageName = true;
			}
			
			$scope.resetSelectPageName = function() {
				$scope.selectedPageName = "";
				$scope.disablePageName = false;
			}

			$scope.validatePageName = function() {
				if(($scope.selectedPageName === undefined || $scope.selectedPageName === "")) {
						return true;
				}
				if($scope.newTestCaseName==='') {
						return true;
					}
					return false;
			}
			
			$scope.addNewTestCase = function($event) {
				
				$scope.testSuite.testCases.push({
					'testCaseName' : $scope.newTestCaseName,
					 'pageName' : $scope.selectedPageName
				});
				$scope.newTestCaseName = "";
				$scope.validatePageName();
			}
			
			

			$scope.removeTestCase = function(index, testCase) {
				$scope.testSuite.testCases.splice(index, 1);
			}

			$scope.saveTestSuite = function() {
				
				$scope.resetSelectPageName();
					
				console.log(JSON.stringify($scope.testSuite));
				$http({
					url : "./testsuites",
					dataType : 'json',
					method : 'POST',
					data : $scope.testSuite,
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					 $scope.successTextAlert = "Test Suite Saved Successfully !";
					    $scope.showSuccessAlert = true;
					$scope.getTestSuites();
					$scope.testSuite = {
							testSuiteName : "",
							testCases : [],
							pageName :""
					};
					$scope.hideTxtBox = false;
				}).error(function(error) {
					$scope.error = error;
				});
			};
			
			$scope.saveTestSuites = [];
			$scope.getTestSuites = function() {
				
				$http({
					url : "./testsuites",
					method : 'GET',
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					console.log(JSON.stringify(response));
					$scope.savedTestSuites = response;
				}).error(function(error) {
					$scope.error = error;
				});
			};
			$scope.getTestSuites();
			//console.log(JSON.stringify($scope.saveTestSuites));
			
			$scope.sort = function(keyname){
		        $scope.sortKey = keyname;   //set the sortKey to the param passed
		        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
		    }		
			
			$scope.editTestSuite = function(testSuite){
				console.log(JSON.stringify(testSuite));
				$scope.testSuite = testSuite;
			};
			$scope.deleteTestSuite = function(testSuite){
				console.log(JSON.stringify(testSuite));
				$http({
					url : "./testsuites/"+testSuite.testSuiteId,
					method : 'DELETE',	
					dataType : 'json',
					params : {"id":testSuite.testSuiteId},
					headers : {
						"Content-Type" : "application/json"
					}
				}).success(function(response) {
					$scope.successTextAlert = "Test Suite : " + testSuite.testSuiteName +" Deleted Successfully !";
				    $scope.showSuccessAlert = true;
					console.log(JSON.stringify(response));
					$scope.getTestSuites();
				}).error(function(error) {
					$scope.error = error;
				});
				
			};
		
	$scope.buildTestSuite = function(){
		$scope.getTestSuites();
		};
			
		} ]);
})();