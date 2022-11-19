# Job Application Manager
Bryan Chang

## Introduction

For the CPSC 210 project, I will be creating a 
job application manager which can keep track of all
your job applications. This application allows you to input 
information about a job/internship of interest. The actual management aspect 
is that users can update their job application status 
to prospective (simply interested in applying) or applied. 
The user can also update the final status which states whether they have 
accepted a job offer, turned down a job offer, 
or got rejected. I will also let the user manually 
remove a job from the manager if they wish to do so.

This target group for this project is mainly anybody 
actively searching for internships or full-time positions, 
especially students. The reason behind this project
is that in my current pursuit of finding internships,
I am managing all the jobs I have in mind on Microsoft
Excel. While this works, it would be nicer to have
some application which will manage all my job applications
more efficiently.

## User Stories
- As a user, I want to be able to add a job to the job application manager.
- As a user, I want to be able to view the list of jobs in the job application manager.
- As a user, I want to be able to add the status of a job as prospective or applied.
- As a user, I want to be able to add the final status of a job to accepted job offer, turned down job offer, or rejected.
- As a user, I want to be able to remove a job from the job application manager.
- (P2) As a user, I want to be able to save all the jobs currently in the job application manager to file.
- (P2) As a user, I want to be able to load all jobs saved into the job application manager from file.

# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by typing in the fields for the job position, company, and selecting an application status from the dropdown.
- You can generate the second required event related to adding Xs to a Y by clicking the "Add Job" button.
- You can locate my visual component by looking at the top right white panel.
- You can save the state of my application by clicking the "Save Jobs" button. Of course, this doesn't do anything if there are no jobs in the visual component.
- You can reload the state of my application by clicking the "Load Jobs" button.