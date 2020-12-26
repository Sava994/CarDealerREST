Car dealer RESTful application.</br>
</br></br>
To run this you will need to have MongoDB installed on your PC.</br>
There are some hardcoded data for testing.</br>
</br>
POST /login with username and password in body you whill get jwt that will alst for 6h and after that you will be able to accsess other url's without need to use password and username aggain.</br>
</br>
<ul>For user there are:</br>
  <li>GET api/users/all --return list of all users</br></li>
  GET api/users/{id} -- return user by id</br>
  POST api/users/search/email -- return user by email (text: test@test.com)</br>
  GET api/users/me -- get current user</br>
  POST api/users/register -- create new user and save it in database </br>
  PUT api/users/update/data/{id} -- update users data (first_name,last_name,email,phone)</br>
  PUT api/users/update/password/{id} -- update users password (password_hash)</br>
  DELETE api/users/delete/{id} -- delete user from database</br></ul
  </br>
For stats there are:</br>
  GET api/stats/dashboard</br>
  GET api/stats/users</br>
  GET api/users/ads</br>
  </br>
For ad there are:</br>
  GET api/ads/all -- return all ads</br>
  GET api/ads/vehicle/{type} -- get ads with vehicle type</br>
  GET api/ads/{id} -- get ad by its id</br>
  POST api/ads/filter -- get ads by filters</br>
  GET api/ads/users/{id} -- get ads for user id</br>
  POST api/ads/create -- create new ad</br>
  PUT api/ads/update/{id} -- update ad by its id</br>
  DELETE api/ads/users/{id} -- delete ad bu its id</br>
