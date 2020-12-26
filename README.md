<b>Car dealer RESTful application.</b></br>
</br></br>
To run this you will need to have MongoDB installed on your PC.</br>
There are some hardcoded data for testing.</br>
</br>
POST /login with username and password in body you will get jwt that will last for 6h so you can use jwt to accsess rest of app.</br>
</br>
For user there are:</br>
  <ul><li>GET api/users/all --return list of all users</br></li>
  <li>GET api/users/{id} -- return user by id</br>
  <li>POST api/users/search/email -- return user by email (text: test@test.com)</br>
  <li>GET api/users/me -- get current user</br>
  <li>POST api/users/register -- create new user and save it in database </br>
  <li>PUT api/users/update/data/{id} -- update users data (first_name,last_name,email,phone)</br>
  <li>PUT api/users/update/password/{id} -- update users password (password_hash)</br>
  <li>DELETE api/users/delete/{id} -- delete user from database</br></ul>
  </br>
For stats there are:</br>
  <ul><li>GET api/stats/dashboard</br>
  <li>GET api/stats/users</br>
  <li>GET api/users/ads</br></ul>
  </br>
For ad there are:</br>
  <ul><li>GET api/ads/all -- return all ads</br>
  <li>GET api/ads/vehicle/{type} -- get ads with vehicle type</br>
  <li>GET api/ads/{id} -- get ad by its id</br>
  <li>POST api/ads/filter -- get ads by filters</br>
  <li>GET api/ads/users/{id} -- get ads for user id</br>
  <li>POST api/ads/create -- create new ad</br>
  <li>PUT api/ads/update/{id} -- update ad by its id</br>
  <li>DELETE api/ads/users/{id} -- delete ad bu its id</br></ul>
