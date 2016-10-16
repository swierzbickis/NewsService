'use strict';

var path = ''

angular.module('RESTService', [])
  .controller('NewsController', function (NewsModel) {
    var newsdashboard = this;

    function getNews() {
      path = '/getNews/'
      NewsModel.all().then(function (result) {
        newsdashboard.news = result.data;
        // console.log(newsdashboard.news);
      })
    }

    newsdashboard.news = [];
    getNews();

  }).controller('CommentController', function ($scope, $routeParams, NewsModel) {

  var newsId = $routeParams.id;
  path = '/getCommentsByNewsId/' + newsId;

  var comm = this;

  var data = new Date().toLocaleString();

  $scope.createComm = function (comment) {
    NewsModel.createComment(angular.extend({},
      {data: data, newsId : newsId},comment)).then(function(result){

      initCreateComm();

    })
  }

  function initCreateComm(){
    comm.newComm = {comment : '', author : ''};
  }

  NewsModel.getCommentById().then(function (result) {
    $scope.comments = result.data;
    console.log($scope.comments);
  });

})
  .controller('NewsDisplayController', function ($scope, $routeParams, NewsModel) {

    //var displaydash = this;
    var newsId = $routeParams.id;

    //displaydash.news=result.data;

    path = '/getNewsById/' + newsId;

    NewsModel.getNewsById().then(function (result) {
      $scope.news = result.data;
    })


  })
  .constant('ENDPOINT_URI', 'http://localhost:8080/api/news')
  .service('NewsModel', function ($http, ENDPOINT_URI) {

    var service = this;

    function getUrl() {
      return ENDPOINT_URI + path;
    }

    service.all = function () {
      return $http.get(getUrl());
    }

    service.create = function (news) {
      return $http.post(getUrl(), news);
    }

    service.getNewsById = function () {
      return $http.get(getUrl());
    }

    service.getCommentById = function () {
      return $http.get(getUrl());
    }

    service.createComment = function(comment){
      return $http.post(getUrl(),comment);
    }

  });
