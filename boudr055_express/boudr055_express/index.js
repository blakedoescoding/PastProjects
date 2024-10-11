// YOU CAN USE THIS FILE AS REFERENCE FOR SERVER DEVELOPMENT

// include the express modules
var express = require("express");

// create an express application
var app = express();
const url = require('url');

// helps in extracting the body portion of an incoming request stream
var bodyparser = require('body-parser'); // this has been depricated, is now part of express...

// fs module - provides an API for interacting with the file system
var fs = require("fs");

// helps in managing user sessions
var session = require('express-session');

// include the mysql module
var mysql = require("mysql");

// Bcrypt library for comparing password hashes
const bcrypt = require('bcrypt');

// A possible library to help reading uploaded file.
// var formidable = require('formidable')

var connection = mysql.createConnection({
  host: 'cse-mysql-classes-01.cse.umn.edu',
  user: 'C4131S24NU10',
  password: '150',
  database: 'C4131S24NU10'
});


// apply the body-parser middleware to all incoming requests
app.use(bodyparser());

// use express-session
// in mremory session is sufficient for this assignment
app.use(session({
  secret: "csci4131secretkey",
  saveUninitialized: true,
  resave: false
}
));

// server listens on port 9007 for incoming connections
app.listen(9007, () => console.log('Listening on port 9007!'));

// middle ware to serve static files
app.use('/static', express.static(__dirname + '/static'));


// function to return the welcome page
app.get('/',function(req, res) {
  res.sendFile(__dirname + '/static/html/welcome.html');
});

// function to return the login page
app.get('/login',function(req, res) {
  res.sendFile(__dirname + '/static/html/login.html');
});

// function to return the schedule page
app.get('/schedule', function(req, res, next) {
  // check if user is logged in
  if (!req.session.user) {
    // if not, redirect to login page
    res.redirect('/login');
  } else {
    // if yes, proceed to next middleware function
    next();
  }
}, function(req, res) {
  res.sendFile(__dirname + '/static/html/schedule.html');
});

// function to get the schedule data
app.get('/getSchedule', function(req, res) {
  const dayOfWeek = req.query.dayOfWeek;
  const sql = 'SELECT * FROM tbl_events WHERE event_day = ? ORDER BY event_start ASC';

  connection.query(sql, [dayOfWeek], (err, result) => {
    if (err) throw err;

    // convert to JSON and send as response
    res.json(result);
  });
});


// function to return the addEvent page
app.get('/addEvent', function(req, res, next) {
  // check if user is logged in
  if (!req.session.user) {
    // if not, redirect to login page
    res.redirect('/login');
  } else {
    // if yes, proceed to next middleware function
    next();
  }
}, function(req, res) {
  res.sendFile(__dirname + '/static/html/addEvent.html');
});

app.get('/editEvent.html', function(req, res, next) {
  // check if user is logged in
  if (!req.session.user) {
    // if not, redirect to login page
    res.redirect('/login');
  } else {
    // if yes, proceed to next middleware function
    next();
  }
}, function(req, res) {
  var eventId = req.query.id; // Get the event ID from the URL query parameters
  res.sendFile(__dirname + '/static/html/editEvent.html');
});



// function to return the 404 message and error to client
app.get('*', function(req, res) {
  // add details
  res.sendStatus(404);
});

app.post('/login', function(req, res) {
  //post request to login, extract username and password from the request body
  var username = req.body.uname;
  var password = req.body.psw;


  //a sql query is executed to find the user in the tbl_accounts table where the acc_login is equal to the username
  connection.query('SELECT * FROM tbl_accounts WHERE acc_login = ?', [username], function(error, results, fields) {
    if (error) {
      // handle error
      console.log(error);
      res.status(500).json({ error: 'Internal server error' });
    } else if (results.length > 0) {
      // if the user is found, compare the password hash with the password entered by the user
      bcrypt.compare(password, results[0].acc_password, function(err, result) {
        if (result == true) {
          // if the password is correct, create a session for the user
          req.session.user = results[0]; // create user session
          res.json({ success: true });
        } else {
          res.status(401).json({ error: 'Invalid username or password' });
        }
      });
    } else {
      res.status(401).json({ error: 'Invalid username or password' });
    }
  });
});

app.post('/addEvent', function(req, res) {
  let eventData = req.body;

  // console.log(eventData); //print out the response for debug

  let sql = `INSERT INTO tbl_events (event_day, event_event, event_start, event_end, event_location, event_phone, event_info, event_url)
             VALUES (?, ?, ?, ?, ?, ?, ?, ?)`;

  connection.query(sql, [eventData.event_day, eventData.event_event, eventData.event_start, eventData.event_end, eventData.event_location, eventData.event_phone, eventData.event_info, eventData.event_url], function(err, result) {
    if (err) throw err;

    res.redirect('/schedule'); // redirect to the Schedule page
  });
});

app.post('/editEvent/:id', function(req, res) {
  // Get the ID of the event from the URL parameters
  var eventId = req.params.id;
  console.log(eventId);

  // Get the new data for the event from the form
  var eventData = req.body;

  let sql = `UPDATE tbl_events SET event_day = ?, event_event = ?, event_start = ?, event_end = ?, event_location = ?, event_phone = ?, event_info = ?, event_url = ? WHERE event_id = ?`;

  // Execute the query
  connection.query(sql, [eventData.event_day, eventData.event_event, eventData.event_start, eventData.event_end, eventData.event_location, eventData.event_phone, eventData.event_info, eventData.event_url, eventId], function(err, result) {
    if (err) throw err;

    res.redirect('/schedule'); // redirect to the Schedule page
  });
});


app.get('/logout', function(req, res){
  req.session.destroy(function(err) {
    if(err) {
      console.log(err);
    } else {
      res.redirect('/login');
    }
  });
});

app.delete('/deleteEvent/:id', function(req, res) {
  let eventId = req.params.id;

  let sql = `SELECT * FROM tbl_events WHERE event_id = ?`;

  connection.query(sql, [eventId], function(err, result) {
    if (err) throw err;

    if (result.length > 0) {
      sql = `DELETE FROM tbl_events WHERE event_id = ?`;

      connection.query(sql, [eventId], function(err, result) {
        if (err) throw err;

        res.status(200).end();
      });
    } else {
      res.status(404).end();
    }
  });
});