(function () {
'use strict';
var app = angular.module('nAutomationApp');
app.controller('testExecutionCtrl',
		function($scope, $window, $location, $http) {
			$scope.pagenames = [ "login", "checkout", "payment" ];
			$scope.DropDownChnaged = function() {
				$scope.selectedPage = $scope.pagenames;
			};

			
			$scope.testSuiteMap = {};
			$scope.testCases = [];
			$scope.projects = [];
			$scope.project = {};
			
			function createTestSuiteObject(data){
				//
				
				data.forEach(function(project){
				  if(project.testSuite && project.testSuite[0]){
					  var suiteStr = project.testSuite[0].testSuiteName ;
					  var caseStr =project.testSuite[0].testCases[0].testCaseName ;
					  if($scope.testSuiteMap[suiteStr]){
						  var testCaseMap = $scope.testSuiteMap[suiteStr];
						  testCaseMap[caseStr] = project.testSuite[0].testCases[0].testCaseName;
					  }else {
						  var testCaseMap = {};
						  testCaseMap[caseStr]= project.testSuite[0].testCases[0].testCaseName;
						  $scope.testSuiteMap[suiteStr] = testCaseMap;
					  }
					}
				});
				
				
				
			}
			
			$scope.changeSuite = function(suiteSeleted) {
				console.log(suiteSeleted);
				$scope.testCases = $scope.testSuiteMap[suiteSeleted];
				console.log($scope.testCases);
				
				$scope.project.testSuite = [{
								'testSuiteName' : suiteSeleted
							}];
			};
			
			$scope.domains = [ "safeway", "Walmart", "Gap", "Target" ];

			$scope.browsers = [ "Chrome", "Firefox", "Safari", "Explorer" ];
			
			$scope.selectedTestCases=[];
			
			$scope.changeCase = function(caseSelected) {
				var testCase = $scope.project.testSuite[0];
				testCase['testCases'] =[];
				
				caseSelected.forEach(function(cases){
					testCase['testCases'].push({
						'testCaseName' : cases
					});
				});
				
				console.log($scope.testCases);
			};

			
			$scope.executeTest = function() {
				var testCases =[];
				console.log('$scope.selectedTestCases ' + $scope.selectedTestCases);
				
				
				console.log('Executing Test with input url ' + JSON.stringify($scope.project));
				$http.post('./executeTest', $scope.project).success(
						function(data, status) {
							//alert("i am" + status);
							
							$scope.report = data;
							$scope.reportHref = data;
						}).error(function(data, status) {
				
				});

				
			};
			
	$scope.buildTestExecution = function(){
		$http.get('./projects').success(
				function(data, status) {
					$scope.projects = data;
					createTestSuiteObject(data);
					console.log('data ' + JSON.stringify(data));
				}).error(function(data, status) {
		});

	};

});



})();