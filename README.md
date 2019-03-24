# CourseRating

Mandatory Assignment 1, Course Rating App
Michael Kongsgaard Jensen
mich953i@stud.kea.dk

Brief explanation of code:

I've focused on keeping the data close knitted, and eliminate as much hard coding as possible.

This is primarily done through Objects and ArrayLists populated through loops, adding the correct values this way. 
This means that with relatively few changes it would be possible to take the data directly from a database, add them to the correct 
objects and transfer between Activities using Parcel, no matter the amount of data needed to be transfered.

I've made sure that all input is bulletproof. The App will not allow you to type anything besides the numbers 1-100, and if a missing
input is registered it'll show a toast and will not continue unless said input is made.

The App is usable in both Portrait and Landscape. This is done through splitting the layouts into two duplicates of each other, and 
shifting the size and constraints individually. 

As a feature not taught in class I've added a reaction gif that changes depending on the calculated grade. This is done through ImageView,
some dependency edits in build.gradle and additions to the correct xml files.

The application is tested on a Samsung S10 with no bugs encountered. 
