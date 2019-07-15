 //function for carTable
$(document).ready(function((){
    
    showAllUsers();
    
});



function showAllUsers(){
    $.ajax({
        url: server_url + 'user',
        method: 'GET',
        contentType: 'application/json',
        comlete: function(serverResponse){
            console.log(serverResponse);
            
            let users = serverResponse.responseJSON;
            $.each(users, function(key, value){
                    
                    
                console.log(value.name)
                
                });
            }
        });
    }
