This is a sample twitter clone application with some basic functionality like :
1) Creating a new user (user registration)
2) Make some user follow some other existing user.
3) Search for a particular user.
4) Create new tweets.
5) Like a particular tweet.
6) Get Feed data for a particular user(tweets made by the users , whom the current user is following)


Steps to start the application in a local environment : 

1) Download STS(spring tool suite) and import the given project as a maven project (all application related config is inside
 application.properties and all dependencies are inside pom.xml)
2) Goto TwitterCloneApplication.java , right click and select 'Start as SpringBoot Application'
3) Default port is setup at 8083 ,in case this needs to be changed,
 then that can be done inside application.properties file inside src/main/resources using property 'server.port'
 
4) Database and the table scripts are inside src/main/resources/script folder.

5) Once the application is started below the created endpoints and their payloads : 

	1) POST : http://localhost:8083/users/create 
	
	This api creates a new user.
	(unique user handle is currently the 'id' inside en_user table which is auto generated primary key)
	
	Payload :
	{
    "name": "Rahul",
	"password" : "00000",
    "age" : 22,
    "totalTweets" : 0
	}
	
	2) POST : http://localhost:8083/users/follow?userId=2&followerId=1 
	
	This api is used to make a user follow some other existing user.
	No payload for this api, userId and follwerId are passed as request parameters (user with specified id will start following user with followerId)
	
	
	3) GET : http://localhost:8083/users/search?userId=1
	
	No payload for this api, userId is passed as request parameter and is used to fetch a particular customer details with a given userID
	Sample Response ://followerList is the list of all people the given user himself follows
	
	{
    "id": 1,
    "name": "Shubham",
    "password": null,
    "age": 25,
    "totalTweets": 0,
    "followerList": [
        2,
        3
    ]
	}
	
	4) POST : http://localhost:8083/tweets/create
	
	This api is used to create a new tweet for a user.Max tweet length can be 140 and userId is necessary to create a tweet.
	
	Payload : 
	{
    "content" : "Tweet 3",
    "userId" : 3,
    "totalLikes" : 0
	}
	
	5) POST : http://localhost:8083/tweets/feed?userId=1
	
	This api is used to get the feed data, feed data includes all the tweets made by the people whom the current user follows
	, userId is passed as the requestParameter for this api.
	
	Sample Response : // userId in response is the userId of the person by whom the tweet was made
	
	[
    {
        "id": 1,
        "content": "Tweet 1",
        "userId": 2,
        "totalLikes": 4
    },
    {
        "id": 2,
        "content": "Tweet 2",
        "userId": 2,
        "totalLikes": 0
    },
    {
        "id": 3,
        "content": "Tweet 3",
        "userId": 3,
        "totalLikes": 0
    }
	]
	
	
	6) POST : http://localhost:8083/tweets/like?tweetId=1
	
	This api is used for increasing the number of likes, for a particular tweet, tweetId is passed as a request parameter for this api.
