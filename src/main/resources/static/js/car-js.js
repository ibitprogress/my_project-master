
let pageNumber = 0;
let pageSize = 10;
let sortBy = 'make,ASC&sort=model,ASC';
let make = null;
let model = null;
let vin = null;
let url =  server_url + 'car/page?page=' + pageNumber + '&size=' + pageSize + '&sort=' + sortBy;
let cars = null;
let firstTimeMake = true;
let firstTimeModel = true;

$(document).ready(function(){
  
    showAllCars(pageNumber, pageSize, sortBy, make, model, vin, url, cars);
    makeSelect();
    
    $('#modalForNewCarInputAddCarBTN').on('click', addCar);
    $('#btnMore').on('click', showMore);
    $('#cardContainerSortByKilk').on('change',function (e) {
        let make = $('#searchInput').val();
        if(make != null){
            pageSize = (e.target.value);
            pageNumber = 0;
            let url =  server_url + 'car/page?page=' + pageNumber + '&size=' + pageSize + '&sort=' + sortBy + '&make=' + make;
            $('#all-cards').empty();
            showAllCars(pageNumber, pageSize, sortBy, make, model, vin, url);
        }else{
           pageSize = (e.target.value);
            pageNumber = 0;
                $('#all-cards').empty();
            showAllCars(pageNumber, pageSize, sortBy, make, model, vin, url); 
        }
          
    });
    $('#cardContainerSortBy').on('change',function(e){
        let make = $('#searchInput').val();
        if(make != null){
            sortBy = (e.target.value);
            let url =  server_url + 'car/page?page=' + pageNumber + '&size=' + pageSize + '&sort=' + sortBy + '&make=' + make;
            $('#all-cards').empty();
            showAllCars(pageNumber, pageSize, sortBy, make, model, vin, url);
        }else{
             sortBy = (e.target.value);
            $('#all-cards').empty();
            showAllCars(pageNumber, pageSize, sortBy, make, model, vin, url);
        }
       
    });    
    $('#searchButton').on('click', function (){
        let array = $('#searchInput').val().split(' '); 
        $.each(array, function(i){
            make = array[0];
            model = array[1];
        });
        let searchExp = new RegExp(/^[a-zA-Z0-9]+$/); 

        if(searchExp.test(make) && (make != null) && model == null && make.length!=17){
            let url =  server_url + 'car/page?page=' + pageNumber + '&size=' + pageSize + '&sort=' + sortBy + '&make=' + make;
            $.ajax({
              url: server_url + 'car/page?make=' + make,
              method: 'GET',
              contentType: 'application/json',
              complete: function(serverResponse){
                    if(serverResponse.responseJSON.totalElements == 0){
                        model = make;
                        $.ajax({
                          url: server_url + 'car/page?page=' + pageNumber + '&size=' + pageSize + '&sort=' + sortBy + '&model=' + model,
                          method: 'GET',
                          contentType: 'application/json',
                          complete: function(serverResponse){
                                cars = serverResponse.responseJSON;
                              $('#all-cards').empty();
                              
                              $.each(cars.content, function(key, value){
                                  $('#all-cards').append(
                                        `
                                        <div class="card flex-row flex-wrap cards wow fadeInRight" id="card-${value.id}">
                                            <div class="card-header col-4">
                                                <div id="carouselCard-${value.id}" class="carousel slide " data-ride="carousel">
                                                      <div class="carousel-inner" id="cardCarosel-${value.id}">
                                                            <div class="carousel-item active">
                                                              <img src="https://picsum.photos/200/300/?blur" class="d-block w-100" alt="...">
                                                            </div>
                                                            <div class="carousel-item">
                                                              <img src="https://picsum.photos/200/300/?random" class="d-block w-100" alt="...">
                                                            </div>
                                                            <div class="carousel-item">
                                                              <img src="https://picsum.photos/200/300/?blur" class="d-block w-100" alt="...">
                                                            </div>
                                                            <div class="carousel-item">
                                                              <img src="https://picsum.photos/200/300" class="d-block w-100" alt="...">
                                                            </div>
                                                            <div class="carousel-item">
                                                              <img src="https://picsum.photos/g/200/300" class="d-block w-100" alt="...">
                                                            </div>
                                                      </div>
                                                      <a class="carousel-control-prev" href="#carouselCard-${value.id}" role="button" data-slide="prev">
                                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                        <span class="sr-only">Previous</span>
                                                      </a>
                                                      <a class="carousel-control-next" href="#carouselCard-${value.id}" role="button" data-slide="next">
                                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                        <span class="sr-only">Next</span>
                                                      </a>
                                                  </div>
                                            </div>

                                            <div class="col-8">
                                               <div class="container pt-3">

                                                    <h2 class="text-center">${value.make + ' ' + value.model}</h2>
                                                        <div class="col-8">
                                                            <p> Body : ${value.body}</p>
                                                            <p> Mileage : ${value.mileage}</p>
                                                            <p> Date of Manufacture : ${value.dateOfManufacture}</p>
                                                            <p> Engine volume : ${value.volume}</p>
                                                            <p> Drive : ${value.drive}</p>
                                                            <p> Descrition : ${value.description}</p>

                                                        </div>   
                                                            <h4 class="text-right"> Price : ${value.price}</h4>
                                                        </div>
                                            </div>
                                        </div>
                                        <br>
                                        `
                                  );
                                $('#carouselCard-'+value.id).carousel();
                              });
                              
                              
                             }
                        });
                        
                    }else{
                        $('#all-cards').empty();
                        showAllCars(pageNumber, pageSize, sortBy, make, model, vin, url, cars);
                    }
                }
            });
        }else if(searchExp.test(make) && (make != null) && model != null && make.length!=17){
            let url =  server_url + 'car/page?page=' + pageNumber + '&size=' + pageSize + '&sort=' + sortBy + '&make=' + make + '&model=' + model;
            $('#all-cards').empty();
            showAllCars(pageNumber, pageSize, sortBy, make, model, vin, url, cars);
        }else if(searchExp.test(make) && make.length == 17 ){
            let vin = $('#searchInput').val();
            let url =  server_url + 'car/page?page=' + pageNumber + '&size=' + pageSize + '&sort=' + sortBy + '&vin=' + vin;
            $('#all-cards').empty();
            showAllCars(pageNumber, pageSize, sortBy, make, model, vin, url, cars);    
        }else if(make.length == 0){
            alert('enter value');
            $('#all-cards').empty();
            showAllCars(pageNumber, pageSize, sortBy, make, model, vin, url, cars);
        }else if(!searchExp.test(make)){
            alert('houston we have a problem');
            $('#searchInput').val(null);
            showAllCars(pageNumber, pageSize, sortBy, make, model, vin, url, cars);
        }
    });
    $('#filtersDropDownMake').on('click',modelSelect);
    $('#modalForNewCarInputFile').on('change',  function(e){
    console.log(e.target.id);
    });
    
    $(document).dblclick( function(e){
        let ele = e.target;
        let ele2 = $(ele).closest('.card');
        
        if(ele2.length > 0){
            let id = $(ele2).data('id');
            showCarById(id);
        }
    });
    

//to top
    if ($('#back-to-top').length) {
        var scrollTrigger = 2000, // px
            backToTop = function () {
                var scrollTop = $(window).scrollTop();
                if (scrollTop > scrollTrigger) {
                    $('#back-to-top').addClass('show');
                } else {
                    $('#back-to-top').removeClass('show');
                }
            };
        backToTop();
        $(window).on('scroll', function () {
            backToTop();
        });
        $('#back-to-top').on('click', function (e) {
            e.preventDefault();
            $('html,body').animate({
                scrollTop: 0
            }, 700);
        });
    }/*end*/
});




//add Car
function addCar(){ 
    let photos = [];
    let p1 = new FormData();
    p1.append('photos', $('#modalForNewCarInputFile').get(0).files[0]);
    
    let p2 = new FormData();
    p2.append('photos', $('#modalForNewCarInputFile-1').get(0).files[0]);
    photos.push(p1);
    photos.push(p2);
    
    
    let newCar = {
        photos : photos,
        category : $('#modalForNewCarInputCategory').val(),
        make : $('#modalForNewCarInputMake').val(),
        model : $('#modalForNewCarInputModel').val(),
        mileage : $('#modalForNewCarInputMileage').val(),
        dateOfManufacture : $('#modalForNewCarInputDateOfManufacture').val(),
        addTime : new Date().toISOString().split("T")[0],
        vin : $('#modalForNewCarInputVin').val(),
        price : $('#modalForNewCarInputPrice').val(),
        volume : $('#modalForNewCarInputVolume').val(),
        drive : $('#modalForNewCarInputDrive').val(),
        transmission : $('#modalForNewCarInputTransmission').val(),
        body : $('#modalForNewCarInputBody').val(),
        fuel : $('#modalForNewCarInputFuel').val(),
        color : $('#modalForNewCarInputColor').val(),
        numberOfDoors : $('#modalForNewCarInputNumberOfDoors').val(),
        numberOfSeats : $('#modalForNewCarInputNumberOfSeats').val(),
        comfort : $('#modalForNewCarInputComfort').val(),
        additionalEquipment : $('#modalForNewCarInputAdditionalEquipment').val(),
        description : $('#modalForNewCarInputDescription').val()
    };
    
    $.ajax({
        url: server_url + 'car/',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newCar),
        complete: function(serverResponse){
            if(serverResponse.status == 201){
                alert('Car added');
                $('#modalForSaleCarForm')[0].reset();
                $('#modalForNewCar').modal('hide');
                $('#all-cards').empty();
                showAllCars(pageNumber, pageSize, sortBy, make, model, vin);
            }else if(serverResponse.status == 400){
                $('#modalForNewCarInputDateOfManufactureError')
            
            }
        }
    });
};
      
//show All Cars And Pagination
function showAllCars(pageNumber, pageSize, sortBy, make, model, vin, url, cars){
    
  $.ajax({
      url: url,
      method: 'GET',
      contentType: 'application/json',
      complete: function(serverResponse){
          cars = serverResponse.responseJSON;
          $.each(cars.content, function(key, value){
                  $('#all-cards').append(
                        `
                        <div class="card flex-row flex-wrap cards wow fadeInRight" style="height: 462px;" data-id="${value.id}" id="card-${value.id}">
                            <div class="card-header p-0 col-4">
                                <div id="carouselCard-${value.id}" data-id="carouselCard-${value.id}" class="carousel slide " data-ride="carousel">
                                      <div class="carousel-inner" id="cardCarosel-${value.id}">
                                            <div class="carousel-item active">
                                              <img src="https://picsum.photos/200/300/?blur" class="d-block w-100" alt="...">
                                            </div>
                                            <div class="carousel-item">
                                              <img src="https://picsum.photos/g/200/300" class="d-block w-100" alt="...">
                                            </div>
                                            <div class="carousel-item">
                                              <img src="https://picsum.photos/200/300/?blur" class="d-block w-100" alt="...">
                                            </div>
                                            <div class="carousel-item">
                                              <img src="https://picsum.photos/200/300" class="d-block w-100" alt="...">
                                            </div>
                                            <div class="carousel-item">
                                              <img src="https://picsum.photos/g/200/300" class="d-block w-100" alt="...">
                                            </div>
                                      </div>
                                      <a class="carousel-control-prev" href="#carouselCard-${value.id}" role="button" data-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Previous</span>
                                      </a>
                                      <a class="carousel-control-next" href="#carouselCard-${value.id}" role="button" data-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Next</span>
                                      </a>
                                  </div>
                            </div>

                            <div class="cardContainer col-8 p-0" data-id="${value.id}>
                               <div class="container">

                                    <h2 class="text-center" style="color: #1067FC;">${value.make + ' ' + value.model}</h2>
                                        <div class="container">
                                            <div class="col-6 p-0" style="float: left;">
                                                <p><i class="fas fa-tachometer-alt"></i> Mileage : ${value.mileage}</p>
                                                <p><i class="fas fa-caret-square-right"></i> Engine volume : ${value.volume}</p>
                                                <p><i class="fas fa-calendar-day"></i> Date of Manufacture : ${value.dateOfManufacture}</p>
                                            </div>
                                            <div class="col-6 p-0" style="float: right;">
                                                <p><i class="fas fa-car-side"></i> Drive : ${value.drive}</p>
                                                <p><i class="fas fa-gas-pump"></i> Fuel : ${value.fuel}</p>
                                                <p><i class="fas fa-code"></i> VIN : ${value.vin}</p>
                                            </div>
                                                <p><i class="fas fa-calendar-plus"></i> Add time : ${value.addTime} </p>
                                                <p><i class="fas fa-hand-holding-heart"></i> Comfort : ${value.comfort}</p>
                                                <p><i class="fas fa-cart-plus"></i> Additional Equipment : ${value.additionalEquipment}</p>
                                                <p><i class="fas fa-book-open"></i> Descrition : ${value.description}</p>

                                        </div>  
                                        <div class="container" >
                                            <h4 style="position:absolute; top: 89%; right: 3%; margin: 5px; color: #0921D8;"> Price : ${value.price}</h4>
                                        </div>
                                </div>
                            </div>
                        </div>
                        <br>

                        

                        `
                  );
                $('#carouselCard-'+value.id).carousel();
              });
      }
  }); 
};

//show big modal for car by id
function showCarById(id){
    let car = null;
    $.ajax({
      url: server_url +'car/'+ id,
      method: 'GET',
      contentType: 'application/json',
      complete: function(serverResponse){
          car = serverResponse.responseJSON;
          
          showCarInfo(car);
          function showCarInfo(value){
          
            $('#modalForCarById').append(
              `
            <div class="modal-dialog modal-xl">
                <div class="modal-content" style="height:700px;" id="modalForCarByIdBodyMain">
                    <div class="modal-header pt-0 pb-0 " >
                            <h4 class="modal-title" style= "padding-left:40%;" id="modalForCarByIdLable"> <i class=" fas fa-car"></i> ${value.make} ${value.model}
                            </h4>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                    </div> 

                    <div class="modal-body">
                            
                            <div class="container-fluid p-0" style="height:100%">
                                <div class="row p-0">
                                  <div class="col-6">
                                    
                                    
                                        <div id="modalForCarByIdCarousel-${value.id}" class="carousel slide" data-ride="carousel">
                                              <ol class="carousel-indicators">
                                                <li data-target="#modalForCarByIdcarousel-${value.id}" data-slide-to="0" class="active"></li>
                                                <li data-target="#modalForCarByIdcarousel-${value.id}" data-slide-to="1"></li>
                                                <li data-target="#modalForCarByIdcarousel-${value.id}" data-slide-to="2"></li>
                                                <li data-target="#modalForCarByIdcarousel-${value.id}" data-slide-to="3"></li>
                                                <li data-target="#modalForCarByIdcarousel-${value.id}" data-slide-to="4"></li>
                                                <li data-target="#modalForCarByIdcarousel-${value.id}" data-slide-to="5"></li>
                                              </ol>

                                              <div class="carousel-inner">
                                                
                                                <div class="carousel-item active" style="height: 100%;">
                                                 <img name="mainPhoto" src="https://picsum.photos/200/300/?blur" class="d-block" alt="..." 
                                                    style="width:100%;height:630px; align-content: center;">
                                                </div>

                                                <div class="carousel-item">
                                                  <img name="mainPhoto" src="https://picsum.photos/200/300/?blur" class="d-block" alt="..." 
                                                    style="width:100%;height:630px; align-content: center;">
                                                </div>
						                        <div class="carousel-item">
                                                  <img name="mainPhoto" src="https://picsum.photos/200/300/?random" class="d-block" alt="..." 
                                                    style="width:100%;height:630px; align-content: center;">
                                                </div>
                                                <div class="carousel-item">
                                                  <img name="mainPhoto" src="https://picsum.photos/200/300/?blur" class="d-block" alt="..."
                                                    style="width:100%;height:630px; align-content: center;">
                                                </div>
                                                <div class="carousel-item">
                                                 <img name="mainPhoto" src="https://picsum.photos/g/200/300" class="d-block" alt="..." 
                                                    style="width:100%;height:630px; align-content: center;">
                                               </div>
                                               <div class="carousel-item">
                                                  <img name="mainPhoto" src="https://picsum.photos/200/300/?random" class="d-block" alt="..." 
                                                    style="width:100%;height:630px; align-content: center;">
                                                </div>

                                            </div>
                                                

                                        </div>
                                          <a class="carousel-control-prev" href="#modalForCarByIdCarousel-${value.id}" role="button" data-slide="prev">
                                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                            <span class="sr-only">Previous</span>
                                          </a>
                                          <a class="carousel-control-next" href="#modalForCarByIdCarousel-${value.id}" role="button" data-slide="next">
                                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                            <span class="sr-only">Next</span>
                                          </a>
                                    </div>
                                
                                



                                <div class="col-6 pl-0">
              
                                            <div class="col-6 p-0" style="float: left;">
                                                <p><i class="fas fa-tachometer-alt"></i> Mileage : ${value.mileage}</p>
                                                <p><i class="fas fa-caret-square-right"></i> Engine volume : ${value.volume}</p>
                                                <p><i class="fas fa-calendar-day"></i> Date of Manufacture : ${value.dateOfManufacture}</p>
                                            </div>
                                            <div class="col-6 p-0" style="float: right;">
                                                <p><i class="fas fa-car-side"></i> Drive : ${value.drive}</p>
                                                <p><i class="fas fa-gas-pump"></i> Fuel : ${value.fuel}</p>
                                                <p><i class="fas fa-code"></i> VIN : ${value.vin}</p>
                                            </div>
                                                <p><i class="fas fa-calendar-plus"></i> Add time : ${value.addTime} </p>
                                                <p><i class="fas fa-hand-holding-heart"></i> Comfort : ${value.comfort}</p>
                                                <p><i class="fas fa-cart-plus"></i> Additional Equipment : ${value.additionalEquipment}</p>
                                                <p><i class="fas fa-book-open"></i> Descrition : ${value.description}</p>
                                        
                                            <h4 style="position:absolute; top: 95%; right: 3%; margin: 5px; color: #0921D8;"> Price : ${value.price}</h4>
                                        
                                
                                </div>
                            
                            </div>
                        
                    </div>

                </div>
              </div>
            `
          );
              $('#modalForCarByIdcarousel-'+value.id).carousel();
          
          };
          
          $('#modalForCarById').modal('show');
          $('#modalForCarById').on('hidden.bs.modal', function(){
            $('#modalForCarById').html('');
          });
          
      }
  }); 
};

//showMoreCars
function showMore(){
    
    let make = $('#searchInput').val();
    if(make!=null){
        pageNumber++;
        let url =  server_url + 'car/page?page=' + pageNumber + '&size=' + pageSize + '&sort=' + sortBy + '&make=' + make;
        pageNumber++;
        showAllCars(pageNumber, pageSize, sortBy, make, model, vin, url, cars);
    }else{
        pageNumber++;
        showAllCars(pageNumber, pageSize, sortBy, make, model, vin, url, cars);
    }
    
};




//filters

//make
function makeSelect(){
    
    let filterMake = null;
    
    if(firstTimeMake){
    firstTimeMake = false;
        $.ajax({
        url: server_url + 'filters/make',
        method: 'GET',
        contentType: 'aplication/json',
        complete: function(serverResponse){
            
            filterMake = serverResponse.responseJSON;
            for(let i=0; i<filterMake.length; i++){
                let make = filterMake[i];
                console.log(make);
            }
            
            $.each(filterMake, function(key, value){
                $('#filtersDropDownMake').append(
                `
                    <option value="${value.id}" id='filtersMake${value.make}'>${value.make}</option>
                `
                );
                
                });
            }
        });
    }
   
    
};
//model
function modelSelect(){
    if(firstTimeModel){
        firstTimeModel = false;
        
        $.ajax({
        url: server_url + 'filters/make',
        method: 'GET',
        contentType: 'aplication/json',
        complete: function(serverResponse){
            filterMake = serverResponse.responseJSON;
            
            console.log(filterMake)
            $.each(filterMake, function(key, value){
                $('#filtersDropDownModel').append(
                `
                    <option value="${value.id}" id='filtersModel${value.model}'>${value.model}</option>
                `
                )
                
                });
            }
        });
    }
};
    
//validation
function validation(){
    
let validationForm = {
        
        modalForNewCarInputCategory : false,
        modalForNewCarInputMake : false,
        modalForNewCarInputModel : false,
        modalForNewCarInputMileage : false,
        modalForNewCarInputDateOfManufacture : false,
        modalForNewCarInputVin : false,
        modalForNewCarInputPrice : false,
        modalForNewCarInputVolume : false,
        modalForNewCarInputDrive : false,
        modalForNewCarInputTransmission : false,
        modalForNewCarInputBody : false,
        modalForNewCarInputFuel : false,
        modalForNewCarInputColor : false,
        modalForNewCarInputNumberOfDoors : false,
        modalForNewCarInputNumberOfSeats : false,
        modalForNewCarInputDescription : false
            
    }

function checkValidationForm(){
    if(validationForm.modalForNewCarInputCategory && validationForm.modalForNewCarInputMake && validationForm.modalForNewCarInputModel && 
      validationForm.modalForNewCarInputMileage && validationForm.modalForNewCarInputDateOfManufacture && validationForm.modalForNewCarInputVolume &&
      validationForm.modalForNewCarInputPrice && validationForm.modalForNewCarInputVolume && validationForm.modalForNewCarInputDrive &&
      validationForm.modalForNewCarInputTransmission && validationForm.modalForNewCarInputBody && validationForm.modalForNewCarInputFuel && 
      validationForm.modalForNewCarInputColor && validationForm.modalForNewCarInputNumberOfDoors && validationForm.modalForNewCarInputNumberOfSeats ){
            $('#modalForNewCarInputAddCarBTN').removeAttr('disabled');
        }else{
            $('#modalForNewCarInputAddCarBTN').attr('disabled', true);
        }
}    
    
function showMessage(field, text){
            $(field).text(text).show();
        };
        
function hideMessage(field){
            $(field).hide();
        };
    
//category 
$('#modalForNewCarInputCategory').on('input', function(){
    let value = $(this).val();
    if(value.length < 2 || value.length > 35){
        showMessage('#modalForNewCarInputCategoryError','lenght must be from 2 to 35');
        validationForm.modalForNewCarInputCategory = false;
        checkValidationForm();
    }else{
        hideMessage('#modalForNewCarInputCategoryError');
        validationForm.modalForNewCarInputCategory = true;
        checkValidationForm();
        let exp = new RegExp(/^[a-zA-Z]+$/);
        if(!exp.test(value)){
            showMessage('#modalForNewCarInputCategoryError','use only Eng letters');
            validationForm.modalForNewCarInputCategory = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForNewCarInputCategoryError');
            validationForm.modalForNewCarInputCategory = true;
            checkValidationForm();
        }
    }
});
    
//make
$('#modalForNewCarInputMake').on('input', function(){
    let value = $(this).val();
    if(value.length < 2 || value.length > 35){
        showMessage('#modalForNewCarInputMakeError','lenght must be from 2 to 35');
        validationForm.modalForNewCarInputMake = false;
        checkValidationForm();
    }else{
        hideMessage('#modalForNewCarInputMakeError');
        validationForm.modalForNewCarInputMake = true;
        checkValidationForm();
        let exp = new RegExp(/^[a-zA-Z]+$/);
        if(!exp.test(value)){
            showMessage('#modalForNewCarInputMakeError','use only Eng letters');
            validationForm.modalForNewCarInputMake = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForNewCarInputMakeError');
            validationForm.modalForNewCarInputMake = true;
            checkValidationForm();
        }
    }
});
    
//model
$('#modalForNewCarInputModel').on('input', function(){
    let value = $(this).val();
    if(value.length < 2 || value.length > 45){
        showMessage('#modalForNewCarInputModelError','lenght must be from 2 to 45');
        validationForm.modalForNewCarInputModel = false;
        checkValidationForm();
    }else{
        hideMessage('#modalForNewCarInputModelError');
        validationForm.modalForNewCarInputModel = true;
        checkValidationForm();
        let exp = new RegExp(/^[a-zA-Z0-9]+$/);
        if(!exp.test(value)){
            showMessage('#modalForNewCarInputModelError','use only Eng letters and numbers');
            validationForm.modalForNewCarInputModel = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForNewCarInputModelError');
            validationForm.modalForNewCarInputModel = true;
            checkValidationForm();
        }
    }
});   
    
//mileage
$('#modalForNewCarInputMileage').on('input', function(){
    let value = $(this).val();
    if(value.length < 0 || value.length > 6){
        showMessage('#modalForNewCarInputMileageError','max mileage- 999 999');
        validationForm.modalForNewCarInputMileage = false;
        checkValidationForm();
    }else{
        hideMessage('#modalForNewCarInputMileageError');
        validationForm.modalForNewCarInputMileage = true;
        checkValidationForm();
        let exp = new RegExp(/^[0-9]+$/);
        if(!exp.test(value)){
            showMessage('#modalForNewCarInputMileageError','use only numbers');
            validationForm.modalForNewCarInputMileage = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForNewCarInputMileageError');
            validationForm.modalForNewCarInputMileage = true;
            checkValidationForm();
        }
    }
});
    
//dateOfManufacture
$('#modalForNewCarInputDateOfManufacture').on('input', function(){
    let value = $(this).val();
    if(value.length != 10){
        showMessage('#modalForNewCarInputDateOfManufactureError','enter date');
        validationForm.modalForNewCarInputDateOfManufacture = false;
        checkValidationForm();
    }else{
        hideMessage('#modalForNewCarInputDateOfManufactureError');
        validationForm.modalForNewCarInputDateOfManufacture = true;
        checkValidationForm();
        let exp = new RegExp(/^\d{4}\-(0?[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])$/);
        if(!exp.test(value)){
            showMessage('#modalForNewCarInputDateOfManufactureError','bad format use: \'yyyy-mm-dd\'');
            validationForm.modalForNewCarInputDateOfManufacture = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForNewCarInputDateOfManufactureError');
            validationForm.modalForNewCarInputDateOfManufacture = true;
            checkValidationForm();
        }
    }
});

//VIN
$('#modalForNewCarInputVin').on('input', function(){
    let value = $(this).val();
    if(value.length != 17){
        showMessage('#modalForNewCarInputVinError','lenght must be 17');
        validationForm.modalForNewCarInputVin = false;
        checkValidationForm();
    }else{
        hideMessage('#modalForNewCarInputVinError');
        validationForm.modalForNewCarInputVin = true;
        checkValidationForm();
        let exp = new RegExp(/^[a-zA-Z0-9]+$/);
        if(!exp.test(value)){
            showMessage('#modalForNewCarInputVinError','use only Eng letters and numbers');
            validationForm.modalForNewCarInputVin = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForNewCarInputVinError');
            validationForm.modalForNewCarInputVin = true;
            checkValidationForm();
        }
    }
});
       
//price
$('#modalForNewCarInputPrice').on('input', function(){
    let value = $(this).val();
    if(value.length > 7){
        showMessage('#modalForNewCarInputPriceError','max price 9 999 999');
        validationForm.modalForNewCarInputPrice = false;
        checkValidationForm();
    }else{
        hideMessage('#modalForNewCarInputPriceError');
        validationForm.modalForNewCarInputPrice = true;
        checkValidationForm();
        let exp = new RegExp(/^[0-9]+$/);
        if(!exp.test(value)){
            showMessage('#modalForNewCarInputPriceError','use only numbers');
            validationForm.modalForNewCarInputPrice = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForNewCarInputPriceError');
            validationForm.modalForNewCarInputPrice = true;
            checkValidationForm();
        }
    }
});
    
//color
$('#modalForNewCarInputColor').on('input', function(){
    let value = $(this).val();
    if(value.length < 2 || value.length > 50){
        showMessage('#modalForNewCarInputColorError','lenght must be from 2 to 35');
        validationForm.modalForNewCarInputColor = false;
        checkValidationForm();
    }else{
        hideMessage('#modalForNewCarInputColorError');
        validationForm.modalForNewCarInputColor = true;
        checkValidationForm();
        let exp = new RegExp(/^[a-zA-Z]+$/);
        if(!exp.test(value)){
            showMessage('#modalForNewCarInputColorError','use only Eng letters');
            validationForm.modalForNewCarInputColor = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForNewCarInputColorError');
            validationForm.modalForNewCarInputColor = true;
            checkValidationForm();
        }
    }
});
     
//volume
$('#modalForNewCarInputVolume').on('input', function(){
    let value = $(this).val();
    if(value.length < 2 || value.length > 5){
        showMessage('#modalForNewCarInputVolumeError','engine value must be from 1 to 99 999');
        validationForm.modalForNewCarInputVolume = false;
        checkValidationForm();
    }else{
        hideMessage('#modalForNewCarInputVolumeError');
        validationForm.modalForNewCarInputVolume = true;
        checkValidationForm();
        let exp = new RegExp(/^[0-9]+$/);
        if(!exp.test(value)){
            showMessage('#modalForNewCarInputVolumeError','only numbers');
            validationForm.modalForNewCarInputVolume = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForNewCarInputVolumeError');
            validationForm.modalForNewCarInputVolume = true;
            checkValidationForm();
        }
    }
});

//drive
$('#modalForNewCarInputDrive').on('input', function(){
    let value = $(this).val();
    if(value.length < 2 || value.length > 50){
        showMessage('#modalForNewCarInputDriveError','lenght must be from 2 to 50');
        validationForm.modalForNewCarInputDrive = false;
        checkValidationForm();
    }else{
        hideMessage('#modalForNewCarInputDriveError');
        validationForm.modalForNewCarInputDrive = true;
        checkValidationForm();
        let exp = new RegExp(/^[a-zA-Z0-9]+$/);
        if(!exp.test(value)){
            showMessage('#modalForNewCarInputDriveError','you can use letters and numbers');
            validationForm.modalForNewCarInputDrive = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForNewCarInputDriveError');
            validationForm.modalForNewCarInputDrive = true;
            checkValidationForm();
        }
    }
});
    
//transmission
$('#modalForNewCarInputTransmission').on('input', function(){
    let value = $(this).val();
    if(value.length < 2 || value.length > 50){
        showMessage('#modalForNewCarInputTransmissionError','lenght must be from 2 to 50');
        validationForm.modalForNewCarInputTransmission = false;
        checkValidationForm();
    }else{
        hideMessage('#modalForNewCarInputTransmissionError');
        validationForm.modalForNewCarInputTransmission = true;
        checkValidationForm();
        let exp = new RegExp(/^[a-zA-Z0-9]+$/);
        if(!exp.test(value)){
            showMessage('#modalForNewCarInputTransmissionError','you can use letters and numbers');
            validationForm.modalForNewCarInputTransmission = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForNewCarInputTransmissionError');
            validationForm.modalForNewCarInputTransmission = true;
            checkValidationForm();
        }
    }
});
    
//body
$('#modalForNewCarInputBody').on('input', function(){
    let value = $(this).val();
    if(value.length < 2 || value.length > 50){
        showMessage('#modalForNewCarInputBodyError','lenght must be from 2 to 35');
        validationForm.modalForNewCarInputBody = false;
        checkValidationForm();
    }else{
        hideMessage('#modalForNewCarInputBodyError');
        validationForm.modalForNewCarInputBody = true;
        checkValidationForm();
        let exp = new RegExp(/^[a-zA-Z]+$/);
        if(!exp.test(value)){
            showMessage('#modalForNewCarInputBodyError','use only Eng letters');
            validationForm.modalForNewCarInputBody = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForNewCarInputBodyError');
            validationForm.modalForNewCarInputBody = true;
            checkValidationForm();
        }
    }
});
    
//fuel
$('#modalForNewCarInputFuel').on('input', function(){
    let value = $(this).val();
    if(value.length < 2 || value.length > 20){
        showMessage('#modalForNewCarInputFuelError','lenght must be from 2 to 35');
        validationForm.modalForNewCarInputFuel = false;
        checkValidationForm();
    }else{
        hideMessage('#modalForNewCarInputFuelError');
        validationForm.modalForNewCarInputFuel = true;
        checkValidationForm();
        let exp = new RegExp(/^[a-zA-Z]+$/);
        if(!exp.test(value)){
            showMessage('#modalForNewCarInputFuelError','use only Eng letters');
            validationForm.modalForNewCarInputFuel = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForNewCarInputFuelError');
            validationForm.modalForNewCarInputFuel = true;
            checkValidationForm();
        }
    }
});
    
//numberOfDoors
$('#modalForNewCarInputNumberOfDoors').on('input', function(){
    let value = $(this).val();
    if(value > 1 || value < 6){
        showMessage('#modalForNewCarInputNumberOfDoorsError','lenght must be from 2 to 5');
        validationForm.modalForNewCarInputNumberOfDoors = false;
        checkValidationForm();
    }else{
        hideMessage('#modalForNewCarInputNumberOfDoorsError');
        validationForm.modalForNewCarInputNumberOfDoors = true;
        checkValidationForm();
        let exp = new RegExp(/^[0-9]+$/);
        if(!exp.test(value)){
            showMessage('#modalForNewCarInputNumberOfDoorsError','use only numbers');
            validationForm.modalForNewCarInputNumberOfDoors = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForNewCarInputNumberOfDoorsError');
            validationForm.modalForNewCarInputNumberOfDoors = true;
            checkValidationForm();
        }
    }
});
   
//numberOfSeats
$('#modalForNewCarInputNumberOfSeats').on('input', function(){
    let value = $(this).val();
    console.log(value);
    if(value > 1 || value < 51){
        showMessage('#modalForNewCarInputNumberOfSeatsError','lenght must be from 2 to 50');
        validationForm.modalForNewCarInputNumberOfSeats = false;
        checkValidationForm();
    }else{
        hideMessage('#modalForNewCarInputNumberOfSeatsError');
        validationForm.modalForNewCarInputNumberOfSeats = true;
        checkValidationForm();
        let exp = new RegExp(/^[0-9]+$/);
        if(!exp.test(value)){
            showMessage('#modalForNewCarInputNumberOfSeatsError','use only numbers');
            validationForm.modalForNewCarInputNumberOfSeats = false;
            checkValidationForm();
        }else{
            hideMessage('#modalForNewCarInputNumberOfSeatsError');
            validationForm.modalForNewCarInputNumberOfSeats = true;
            checkValidationForm();
        }
    }
});
    

    
};







































