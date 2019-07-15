$(document).ready(function(){
    
    let authorizationToken = localStorage.getItem('auth_token');
    
    
    
    $('#modalForExitBtn').hide();
    $('#modalForMessageBtn').hide();
    $('#modalForFavouriteBtn').hide();
    $('#saleCar').hide();
    
    if(authorizationToken){
        $('#modalForLogInBtn').hide();
        $('#modalForRegistryBtn').hide();
        $('#modalForMessageBtn').show();
        $('#modalForFavouriteBtn').show();
        $('#modalForExitBtn').show();
        $('#saleCar').show();
        
        $('#modalForExitBtn').on('click', function(){
        localStorage.removeItem('auth_token');
            window.location.href = 'index.html';
        });
        
        $.ajaxSetup({
            headers : {
                'Authorization' : 'Bearer ' + authorizationToken
            }
        });
        
        let role = JSON.parse(atob(authorizationToken.split('.')[1]));
        
        if(role.auth == 'ROLE_USER'){
        console.log('hello user');
        }else if(role.auth == 'ROLE_ADMIN'){
        console.log('hello admin');
        } 
        
    }
    
    
//    showAllUsers();
    $('#modalForLogInButton').on('click', logIn);
    $('#modalForRegistryButtonSubmitRegistry').on('click', registry);
    validateAllFields();

    
});

function showMessage(field, text){
            $(field).text(text).show();
        };

//validation
function validateAllFields(){

    
let validationForm = {
        modalForRegistryName: false,
        modalForRegistrySurname: false,
        modalForRegistryTelephone: false,
        modalForRegistryEmail : false,
        modalForRegistryPassword : false,
        modalForRegistryPasswordConfirm : false,
        modalForRegistryAge : false 
    };

   
function checkValidationForm(){
        if(validationForm.modalForRegistryName && validationForm.modalForRegistrySurname && validationForm.modalForRegistryTelephone 
          && validationForm.modalForRegistryEmail && validationForm.modalForRegistryPassword && validationForm.modalForRegistryPasswordConfirm &&
          validationForm.modalForRegistryAge){
            $('#modalForRegistryButtonSubmitRegistry').removeAttr('disabled');
        }else{
            $('#modalForRegistryButtonSubmitRegistry').attr('disabled', true);
        }
    };
     
    
function hideMessage(field){
            $(field).hide();
        };
//name validation     
    $('#modalForRegistryName').on('input', function(){
        let userName = $(this).val();
            
        if (userName.length < 2 || userName.length > 20){
            showMessage('#modalForRegistryNameError', 'Length must be from 2 to 20');
            validationForm.modalForRegistryName = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForRegistryNameError');
            validationForm.modalForRegistryName = true;
            checkValidationForm();
            let nameExp = new RegExp(/^[a-zA-ZА-ЩЬЮЯҐЄІЇа-щьюяґєії]+$/);
            if(!nameExp.test(userName)){
                showMessage('#modalForRegistryNameError', 'Must not have any special characters');
                validationForm.modalForRegistryName = false;
                checkValidationForm();
            }else{
                hideMessage('#modalForRegistryNameError');
                validationForm.modalForRegistryName = true;
                checkValidationForm();
            }
        }
    });
//surname validation    
     $('#modalForRegistrySurname').on('input', function(){
            
         let userSurname = $(this).val();
         
         if(userSurname.length < 2 || userSurname > 20){
             showMessage('#modalForRegistrySurnameError', 'Length must be from 2 to 20');
             validationForm.modalForRegistrySurname = false;
             checkValidationForm();
         }else{
             hideMessage('#modalForRegistrySurnameError');
             validationForm.modalForRegistrySurname = true;
             checkValidationForm();
             let surnameExp = new RegExp(/^[a-zA-ZА-ЩЬЮЯҐЄІЇа-щьюяґєії]+$/);
             if(!surnameExp.test(userSurname)){
                 showMessage('#modalForRegistrySurnameError', 'Must not have any special characters');
                 validationForm.modalForRegistrySurname = false;
             checkValidationForm();
             }else{
                hideMessage('#modalForRegistrySurnameError');
                validationForm.modalForRegistrySurname = true;
                checkValidationForm();
             }
         }
        });
//telephone validation 
    $('#modalForRegistryTelephone').on('input', function(){
       
        let userTelephone = $(this).val();
        
        if(userTelephone.length != 12){
            showMessage('#modalForRegistryTelephoneError', 'Telephone number must have 12 sumbols');
            validationForm.modalForRegistryTelephone = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForRegistryTelephoneError');
            validationForm.modalForRegistryTelephone = true;
            checkValidationForm();
            let telephoneExp = new RegExp(/^[0-9]+$/);
            if(!telephoneExp.test(userTelephone)){
                showMessage('#modalForRegistryTelephoneError', 'Must not have any special characters');
                validationForm.modalForRegistryTelephone = false;
                checkValidationForm();
            }else{
                hideMessage('#modalForRegistryTelephoneError');
                validationForm.modalForRegistryTelephone = true;
                checkValidationForm();
            }
        }
    });
//email validation
    $('#modalForRegistryEmail').on('input', function(){
       
        let userEmail = $(this).val();
        
        if(userEmail.length < 10 || userEmail.length > 100){
            showMessage('#modalForRegistryEmailError', 'Length must be from 10 to 100');
            validationForm.modalForRegistryEmail = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForRegistryEmailError');
            validationForm.modalForRegistryEmail = true;
            checkValidationForm();
            let telephoneExp = new RegExp(/^[a-z0-9_-]+@[a-z0-9.-]+\.[a-z]{2,3}$/);
            if(!telephoneExp.test(userEmail)){
                showMessage('#modalForRegistryEmailError', 'Not valid email');
                validationForm.modalForRegistryEmail = false;
                checkValidationForm();
            }else{
                hideMessage('#modalForRegistryEmailError');
                validationForm.modalForRegistryEmail = true;
                checkValidationForm();
            }
        } 
    });
//password validation  
    $('#modalForRegistryPassword').on('input', function(){
       
        let userPassword = $('#modalForRegistryPassword').val();
        
        if(userPassword.length < 8 || userPassword.length > 30){
            showMessage('#modalForRegistryPasswordError', 'Length must be from 8 to 30');
            validationForm.modalForRegistryPassword = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForRegistryPasswordError');
            validationForm.modalForRegistryPassword = true;
            checkValidationForm();
            let passwordExp = new RegExp(/^([a-zA-Z0-9])(?=.{8,})/);
            if(!passwordExp.test(userPassword)){
                showMessage('#modalForRegistryPasswordError', 'The string must contain 8 letters and numbers');
                validationForm.modalForRegistryPassword = false;
                checkValidationForm();
            }else{
               hideMessage('#modalForRegistryPasswordError');
                validationForm.modalForRegistryPassword = true;
                checkValidationForm(); 
            }
        } 
    });   
//password confirm validation
    $('#modalForRegistryPasswordConfirm').keyup(function(){
       
        let userPassword = $('#modalForRegistryPassword').val();
        let userPasswordConfirm = $('#modalForRegistryPasswordConfirm').val();
        
       if(userPasswordConfirm != userPassword){
            showMessage('#modalForRegistryPasswordConfirmError', 'Password must be the same');
            validationForm.modalForRegistryPasswordConfirm = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForRegistryPasswordConfirmError');
            validationForm.modalForRegistryPasswordConfirm = true;
            checkValidationForm();
        }
        
    });
//age validation  
    $('#modalForRegistryAge').on('input', function(){
       
        let userAge = $('#modalForRegistryAge').val();
        
        if(userAge < 12 || userAge > 90){
            showMessage('#modalForRegistryAgeError', 'Age must be from 12 to 90');
            validationForm.modalForRegistryAge = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForRegistryAgeError');
            validationForm.modalForRegistryAge = true;
            checkValidationForm();
            let ageExp = new RegExp(/^[0-9]/);
            if(!ageExp.test(userAge)){
                showMessage('#modalForRegistryAgeError', 'Input only numbers');
                validationForm.modalForRegistryAge = false;
                checkValidationForm();
            }else{
                hideMessage('#modalForRegistryAgeError');
                validationForm.modalForRegistryAge = true;
                checkValidationForm();
            }
        }
        
    });

};

//log in
function logIn(){
    let logInData = {
        email : $('#modalForLogInEmail').val(),
        password : $('#modalForLogInPassword').val()
    }
    
     $.ajax({
        url: server_url + 'auth/signin',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(logInData),
        complete: function(serverResponse){
            let result = serverResponse.responseJSON;
            
            if(serverResponse.status == 200){
                if(result.token){
                    localStorage.setItem('auth_token', result.token);
                    window.location.href = 'index.html';
                }
            }
            
        }
    }); 
    
    
};

//registry
function registry(){
    
    let user = {
        firstName : $('#modalForRegistryName').val(),
        surName : $('#modalForRegistrySurname').val(),
        telephone : $('#modalForRegistryTelephone').val(),
        email : $('#modalForRegistryEmail').val(),
        password : $('#modalForRegistryPassword').val(),
        passwordConfirm: $('#modalForRegistryPasswordConfirm').val(),
        gender : $('#modalForRegistryGender').val(),
        age : $('#modalForRegistryAge').val(),
        registryDate : new Date().toISOString().split("T")[0]    
    };
    
//PostMapping USER 
    $.ajax({
        url: server_url + 'auth/signup',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(user),
        complete: function(serverResponse){
            let serverRes = serverResponse.responseJSON;
            if(serverResponse.status == 400){
                $.each(serverRes.errors, function(key, value){
                    if(value.field == 'telephone'){
                        showMessage('#modalForRegistryTelephoneError', value.defaultMessage);
                    }
                    if(value.field == 'email'){
                        showMessage('#modalForRegistryEmailError', value.defaultMessage);
                    }
                });
            }else if(serverResponse.status == 201){
                alert('registred');
                $('#modalForRegistryForm')[0].reset();
                $('#modalForRegistry').modal('hide');
            }
        }
    }); 
};

//showAllUsers
function showAllUsers(){
  $.ajax({
      url: server_url + 'user',
      method: 'GET',
      contentType: 'application/json',
      complete: function(serverResponse){
      }
  });
};