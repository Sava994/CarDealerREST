Car dealer RESTful application.

To run this you will need to have MongoDB installed on your PC.
There are some hardcoded data for testing.

POST /login with username and password in body you whill get jwt that will alst for 6h and after that you will be able to accsess other url's without need to use password and username aggain.

For user there are:</br>
  GET api/users/all --return list of all users
  GET api/users/{id} -- return user by id
  POST api/users/search/email -- return user by email (text: test@test.com)
  GET api/users/me -- get current user
  POST api/users/register -- create new user and save it in database 
  PUT api/users/update/data/{id} -- update users data (first_name,last_name,email,phone)
  PUT api/users/update/password/{id} -- update users password (password_hash)
  DELETE api/users/delete/{id} -- delete user from database
  
For stats there are:
  GET api/stats/dashboard
  GET api/stats/users
  GET api/users/ads
  
For ad there are:
  GET api/ads/all -- return all ads
  GET api/ads/vehicle/{type} -- get ads with vehicle type
  GET api/ads/{id} -- get ad by its id
  POST api/ads/filter -- get ads by filters
  GET api/ads/users/{id} -- get ads for user id
  POST api/ads/create -- create new ad
  PUT api/ads/update/{id} -- update ad by its id
  DELETE api/ads/users/{id} -- delete ad bu its id
