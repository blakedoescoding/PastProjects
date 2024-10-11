username: charlie
password: tango

MySQL [C4131S24NU10]> SELECT acc_login, acc_password FROM tbl_accounts;
+-----------+--------------------------------------------------------------+
| acc_login | acc_password                                                 |
+-----------+--------------------------------------------------------------+
| charlie   | $2b$10$fi0tuEQ75gBVPk3QUcTPoOwMYhl6J4TmHfugLIsNz44U1ErjnJrYG |
+-----------+--------------------------------------------------------------+


from the grades on canvas: sql user and pass: account:C4131S24NU10 passwords:150

NOTE: I included most JS on the html file cause on the lab machines I was having issues with the pathing.


When you start to server you see the welcome page, when you click navigate to website you are sent to the login
page.

Everything seems to work. When you enter in charlie and tango, which is the user and pass in sql, 
you will be logged in. If you enter something random liek cake and manager, you will get an incorrect error. 

From the login screen you are redirected to the schedule page. You can see the dates and events. If you hover over an event
a edit and delete button appears. If you delete the event is deleted. 

If you click the edit button, you are sent to the editEvent page where you can edit that specific event. On sunday, if
I have two events, one for brunch and one for tennis, If I edit the tennis event to lets say be called football, then
only that specific event is updated and not the brunch event. 

Also you can add events as per the homework 6. There is a logout button that when clicked will redirect you to the 
welcome page. 

Note:When you start the server, with node index.js: if you go to http://localhost:9007/schedule or addEvent or editEvent
it will redirect you to the login page. I put the redirect code in the index.js file. 

Schedule page: Same code from assignment 5. Added comments for the edit and delete button, also can handle adding
events per the assignment 6 requirements. Also i added a logout button that when clicked, redirects the user
to the home page. 

AddEvent Page: Added the comments for the eevnt listiner that is logging the form submission. Just like in assignment 5,
when i click on submit, the event is added and the user is redirected to the schedule page. 