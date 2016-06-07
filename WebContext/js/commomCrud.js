/**
 * 
 */
var app = angular.module("UserManagement", []);             
            //Controller Part  
            app.controller("CommonRestController", function($scope, $http) {
            	
            	
            	 $http.get('resources/connection.properties').then(function (response) {
            		 alert("response.data.url :: "+response.data.url);
            		 alert("response.data.method :: "+response.data.method);
            		    console.log('a is ', response.data.url);
            		  });
            	 
            	 
                $scope.user = [];  
                $scope.userForm = {  
                    id : -1,  
                    username : "",  
                    name : ""  
                };  
           
                //Now load the data from server  
                _refreshUserData();  
           
                //HTTP POST/PUT methods for add/edit country   
                // with the help of id, we are going to find out whether it is put or post operation  
                  
                $scope.submitUser = function() {  
                    var method = "";  
                    var url = ""; 
				//	  var conn_url = connection.properties.url;
				//	  alert("conn_url :: "+conn_url);
                    // $scope.userForm.id = user.id;                 
                    if ($scope.userForm.id == -1) {  
                        //Id is absent in form data, it is create new country operation  
                        method = "POST";  
                        url = 'http://localhost:8080/Test_Webservices/WebUserDetails/CreateUser';  
                    } else {  
                        //Id is present in form data, it is edit country operation  
                        method = "PUT";  
                        url = 'http://localhost:8080/Test_Webservices/WebUserDetails/UpdateUser/'+ $scope.userForm.id ;  
                    }                    
       
                     $http({  
                        method : method,  
                        url : url,  
                        data : angular.toJson($scope.userForm),  
                        headers : {  
                            'Content-Type' : 'application/json'  
                        }  
                    }).then( _success, _error );   
                };  
           
                //HTTP DELETE- delete user by Id  
                $scope.deleteUser = function(user) {
                	///DeleteUser
                    $http({  
                        method : 'DELETE',  
                        url : 'http://localhost:8080/Test_Webservices/WebUserDetails/DeleteUser/' + user.id  
                    }).then(_success, _error);  
                };  
   
             // In case of edit, populate form fields and assign form.id with country id  
                $scope.editUser = function(user) {  
                    $scope.userForm.username= user.username;  
                    $scope.userForm.name= user.name;  
                    $scope.userForm.id = user.id;  
                };  
           
                /* Private Methods */  
                //HTTP GET- get all users collection  
                function _refreshUserData() {  
                    $http({  
                        method : 'GET',  
                        url : 'http://localhost:8080/Test_Webservices/WebUserDetails/UserDetails'  
                    }).then(function successCallback(response) {  
                        $scope.user = response.data;  
                    }, function errorCallback(response) {                     	
                        console.log(response.statusText);  
                    });  
                }  
           
                function _success(response) {  
                    _refreshUserData();  
                    _clearFormData()  
                }  
           
                function _error(response) {  
                    console.log(response.statusText);  
                }  
           
                //Clear the form  
                function _clearFormData() {  
                    $scope.userForm.id = -1;  
                    $scope.userForm.username= "";  
                    $scope.userForm.name= "";  
                  
                };  
            });  
   