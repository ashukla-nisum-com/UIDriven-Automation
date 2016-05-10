(function () {
'use strict';
var app = angular.module('nAutomationApp');
app.controller('reportCtrl',
		function($scope, $window, $location, $http) {
			
			$scope.reports = [];
			$scope.sortType = 'name'; // set the default sort type
			$scope.sortReverse = false; // set the default sort order
			$scope.searchReport = ''; // set the default search/filter term

			$scope.today = function(d1) {
				d1 = new Date();
			};
			$scope.today();
			$scope.clear = function(d1) {
				d1 = null;
			};

			var todaysDate = new Date();

			$scope.dateOptions = {

				formatYear : 'yy',
				maxDate : new Date(2020, 5, 22),
				minDate : new Date(todaysDate.getFullYear(), todaysDate
						.getMonth() - 6, todaysDate.getDate()),
				startingDay : 1
			};

			// Disable weekend selection
			function disabled(data) {
				var date = data.date, mode = data.mode;
				return mode === 'day'
						&& (date.getDay() === 0 || date.getDay() === 6);
			}
			$scope.open1 = function() {
				$scope.popup1.opened = true;
			};

			$scope.open2 = function() {
				$scope.popup2.opened = true;
			};

			$scope.setDate = function(year, month, day) {
				$scope.dt = new Date(year, month, day);
			};

			$scope.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy',
					'shortDate' ];
			$scope.format = $scope.formats[0];
			$scope.altInputFormats = [ 'M!/d!/yyyy' ];

			$scope.popup1 = {
				opened : false
			};

			$scope.popup2 = {
				opened : false
			};
			function getDayClass(data) {
				var date = data.date, mode = data.mode;
				if (mode === 'day') {
					var dayToCheck = new Date(date).setHours(0, 0, 0, 0);

					for (var i = 0; i < $scope.events.length; i++) {
						var currentDay = new Date($scope.events[i].date)
								.setHours(0, 0, 0, 0);

						if (dayToCheck === currentDay) {
							return $scope.events[i].status;
						}
					}
				}

				return '';
			}
			
			$scope.buildExecutionReport = function(){

				$http.get("./report?input=s").success(function(data, status) {
					$scope.reports = data;
				}).error(function(data, status) {
					console.log("failure message: " + JSON.stringify(data));
				});

			};
			
			
			
			
		});

function parseDate(date) {

	date = new Date(date);
	var str = date.toISOString().slice(0, 10);
	date = new Date(str);
	return date;
}

app.filter('FilterByDate', function() {
	return function(items, dateFrom, dateTo) {
		if (dateFrom == "" || dateFrom == undefined) {
			return items;
		}
		;
		if (dateTo == "" || dateTo == undefined) {
			return items;
		}
		;

		var filtered = [];

		for (var i = 0; i < items.length; i++) {
			var item = items[i];

			var r = parseDate(item.reportDate);
			var f = parseDate(dateFrom);
			var t = parseDate(dateTo);

			if (r.getTime() >= f.getTime() && r.getTime() <= t.getTime()) {
				filtered.push(item);
			}
		}

		return filtered;
	};
});
})();