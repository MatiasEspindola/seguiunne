// This example displays an address form, using the autocomplete feature
// of the Google Places API to help users fill in the information.









$.getScript('https://maps.googleapis.com/maps/api/js?key=AIzaSyD1ejpdyKbOS4OsQW2vmnGsJbWQhrTm2CI&libraries=places&callback=initMap', function ()
{


});


// The following example creates a marker in Stockholm, Sweden using a DROP
// animation. Clicking on the marker will toggle the animation between a BOUNCE
// animation and no animation.


function initMap() {
    var markersArray = [];
    var bounds = new google.maps.LatLngBounds;
    var input = document.getElementById('dire');
    var autocomplete = new google.maps.places.Autocomplete(input);

    var geocoder = new google.maps.Geocoder;
    var service = new google.maps.DistanceMatrixService;

    var icons = {
        unne: {
            name: 'Unne',
            icon: 'http://maps.google.com/mapfiles/kml/pal3/icon31.png'
        },
        destin: {
            name: 'Tarea',
            icon: 'http://maps.google.com/mapfiles/kml/pal2/icon13.png'
        }
    };
    
    var loc = $('#lat').val().split(';');

    var origenSaved = loc[0].toString().replace("(", "").replace(")", "").split(",");
    var destinoSaved = loc[1].toString().replace("(", "").replace(")", "").split(",");
    
    var posOr = new google.maps.LatLng(origenSaved[0], origenSaved[1]);
    var posDes = new google.maps.LatLng(destinoSaved[0], destinoSaved[1]);


    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 12,
        center: {lat: -29.140563, lng: -59.25801260000003}
    });



    //var posOr = new google.maps.LatLng(-29.140563, -59.25801260000003);
    //var posDes = new google.maps.LatLng(-27.463, -58.985);
    
   
   
//   if( loc[0].toString() == "(-27.463, -58.985000000000014)"){
//       $('.checkunne')[0].checked = true;
//   }else{
//       $('.checkunne')[1].checked = true;
//   }
    
    setPoint();
    


    var geocoder = new google.maps.Geocoder();

    document.getElementById('submit').addEventListener('click', function () {
        geocodeAddress(geocoder, map);
    });
      
    



    function setPoint() {

        markeOrigen = new google.maps.Marker({
            position: posOr,
            map: map,
            icon: icons['unne'].icon,

        });
        marker = new google.maps.Marker({
            position: posDes,
            map: map,
            icon: icons['destin'].icon,
            draggable: true,
        });
        cal();
        google.maps.event.addListener(marker, 'dragend', function (evt) {
            posDes = new google.maps.LatLng(evt.latLng.lat(), evt.latLng.lng());
            cal();

        });

        var legend = document.getElementById('legend');
        for (var key in icons) {
            var type = icons[key];
            var name = type.name;
            var icon = type.icon;
            var div = document.createElement('div');
            div.innerHTML = '<img src="' + icon + '"> ' + name;
            legend.appendChild(div);
        }

        map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(legend);
    
    }

    function  cal() {


        $('#lat').val(posOr +';'+posDes);
        service.getDistanceMatrix({
            origins: [posOr],
            destinations: [posDes],
            travelMode: 'DRIVING',
            unitSystem: google.maps.UnitSystem.METRIC,
            avoidHighways: false,
            avoidTolls: false
        }, function (response, status) {
            if (status !== 'OK') {
                alert('No se puede calcular distancia');
            } else {
                var originList = response.originAddresses;
                var destinationList = response.destinationAddresses;
                var outputDiv = document.getElementById('output');
                outputDiv.innerHTML = '';
                deleteMarkers(markersArray);



                for (var i = 0; i < originList.length; i++) {
                    var results = response.rows[i].elements;

                    for (var j = 0; j < results.length; j++) {
                        outputDiv.innerHTML += "<h2> Este tarea se encuentra a " + results[j].distance.text + " aprox. de la UNNE</h2>";
                    }
                }
            }
        });


    }

    document.getElementById('submit').addEventListener('click', function () {
        geocodeAddress(geocoder, map);
    });





    function geocodeAddress(geocoder, resultsMap) {
        var address = document.getElementById('address').value;
        geocoder.geocode({'address': address.toString().toLocaleLowerCase()}, function (results, status) {
            if (status === 'OK') {
                // Si hay resultados encontrados, centramos y repintamos el mapa
                // esto para eliminar cualquier pin antes puesto
                resultsMap.setCenter(results[0].geometry.location);
                posDes = results[0].geometry.location;
                map.fitBounds(results[0].geometry.viewport);
                marker.setPosition(results[0].geometry.location);
                cal();

            } else {        
                alert("Error al ingresar dirección, por favor intente de nuevo.");
            }
        });
    }

    

}



function toggleBounce() {
    if (marker.getAnimation() !== null) {
        marker.setAnimation(null);
    } else {
        marker.setAnimation(google.maps.Animation.BOUNCE);
    }
}





function deleteMarkers(markersArray) {
    for (var i = 0; i < markersArray.length; i++) {
        markersArray[i].setMap(null);
    }
    markersArray = [];
}