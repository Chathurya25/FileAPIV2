Report Upload 
- As a patient I want to upload a report and a message related to that report.

Functionalities
- Ability to upload a file
- Ability to pass a message with a file
- Success or failure ---> status 

Backend 
- Create an end point to upload a file and a message 

PUT localhost:8080/api/v1/file/uploadDocument
Content-Type: multipart/form-data; 
Accept: application/json
HTTP/1.1
Content-Disposition: form-data; name="file"; 

{
  "message": "this is a message",
  "owner": "chathurya"
}

Create a service class to save the file as binary in google firebase 
and save all the metadata related to the file
in the filetabale in the MySQL database.

Metadata should include the following : 

Filename 
filetype
fileid - random UUiD
The owner
Uploaded date & time
Message 


Expected response

HTTP/1.1 201 Created
Content-Type: application/json

{
  "isSuccessful": <True>
}


Frontend 

Create an interface as shown in the figma to upload a file 
and show a success or failure message depend on the responds.

