var planeX;
var planeY;
var selectedGate = 0;
var gates = [];
var routeToGate = [];
var routePoints = [];
var planeSource = new ol.source.Vector();
var gatesSource = new ol.source.Vector();
var routeSource = new ol.source.Vector();

var gateStyle = new ol.style.Style({
	image: new ol.style.Icon({
		color: [0, 255, 0],
		anchor: [0.25, 0.25],
		anchorXUnits: 'fraction',
		anchorYUnits: 'fraction',
		size: [48, 48],
		opacity: 1,
		crossOrigin: 'anonymous',
		src: 'https://openlayers.org/en/v4.1.1/examples/data/dot.png'
	})
});

var planeStyle = new ol.style.Style({
	image: new ol.style.Icon({
		color: [255, 0, 0],
		crossOrigin: 'anonymous',
		src: 'https://openlayers.org/en/v4.1.1/examples/data/dot.png'
	})
});
	
var routeStyle = new ol.style.Style({
	stroke: new ol.style.Stroke({
		color: [0, 0, 255],
		width: 3
	})
});

function init (){	
	var osmMap = new ol.layer.Tile({
		source: new ol.source.OSM()
    });	
	
	var gatesLayer = new ol.layer.Vector({ source: gatesSource });	
    var planeLayer = new ol.layer.Vector({ source: planeSource });		
	var routeLayer = new ol.layer.Vector({ source: routeSource });
	
    var map = new ol.Map({
        target: 'map',
        layers: [osmMap, gatesLayer, planeLayer, routeLayer],
        view: new ol.View({
        center: ol.proj.fromLonLat([18.4824,54.3751]),
        zoom: 14.75
        })
    });
	setGate();
	getGatesPositions();
	getPlanePosition();
	getRouteToGate();
}

function getGatesPositions(){
	$.ajax({
        type: "GET",
        url: "http://localhost:8080/getGatesPositions",
		dataType: "json",
        async: false,
		success: function(response){
			var json = JSON.parse(JSON.stringify(response));
			gates = json;
			
			for(var i = 0, len = gates.length; i < len; i++)
			{
				var gateFeature = new ol.Feature({
					geometry: new ol.geom.Point(ol.proj.fromLonLat([gates[i].y,gates[i].x]))
				});		
                gateFeature.setStyle(gateStyle);
				gatesSource.addFeature(gateFeature);
			}      
		},
		error: function(e){
			console.log("getPosition request error " + e);
		}
    });
}	

function getRouteToGate(){
	$.ajax({
        type: "GET",
        url: "http://localhost:8080/getRouteToGate",
		dataType: "json",
        async: false,
		success: function(response){
			var json = JSON.parse(JSON.stringify(response));			
			routeToGate = json;
			
			routePoints.length = 0;
			var planePosition = [ planeY, planeX ];
			routePoints.push(planePosition);
			
			for(var i = 1, len = routeToGate.length; i < len; i++)
			{
				var point = [ routeToGate[i].y, routeToGate[i].x ];
				routePoints.push(point);
			}     
			
			for (var i = 0; i < routePoints.length; i++) {
				routePoints[i] = ol.proj.transform(routePoints[i], 'EPSG:4326', 'EPSG:3857');
			}		
			var routeFeature = new ol.Feature({
				geometry: new ol.geom.LineString(routePoints)
			});
			routeSource.clear();
			routeFeature.setStyle(routeStyle);
			routeSource.addFeature(routeFeature);
		},
		error: function(e){
			console.log("getPosition request error " + e);
		}
    });
}

function getPlanePosition(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/getCurrentPosition",
		dataType: "json",
        async: false,
		success: function(response){
			console.log("Connected");
			document.getElementById("connection_diod").style.backgroundColor = "green";
			var json = JSON.parse(JSON.stringify(response));
			planeX = json.x;
			planeY = json.y;
					
			var planeFeature = new ol.Feature({
				geometry: new ol.geom.Point(ol.proj.fromLonLat([planeY, planeX]))
			});
			
			planeFeature.setStyle(planeStyle);
			planeSource.clear();
			planeSource.addFeature(planeFeature);	

			getRouteToGate();
		},
		error: function(e){
			console.log("getPosition request error " + e);
			console.log("Not connected");
			document.getElementById("connection_diod").style.backgroundColor = "red";
		}
    });
}

function selectionChanged(index){
	select = document.getElementById("selectId");
	selectedGate = select.options[select.selectedIndex].text;
	console.log("new Gate selected: " + selectedGate);
	setGate();	
	getRouteToGate();		
}
	
function setGate(){
	var params = "id=" +  selectedGate;
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/setGate?" + params,
		dataType: "json",
		async: true,
		success: function(response){
			console.log("setGate request's response: " + response);
		},
		error: function(e){
			console.log("setGate request error " + e);
		}
	});
}