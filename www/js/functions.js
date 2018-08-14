
var startTime, endTime, speed
var started = false;

function start() {
  startTime = new Date();
  speed = 0;
};

function end() {
  endTime = new Date();
  speed = endTime - startTime;
  console.log("Time diff: "+speed);
}

$('#inputPassword').keydown(function() {
    if(!started){
        start();
        started = true;
        console.log("Started: "+startTime);
    }    
    console.log('keypress');
});

$('form').on('submit', function() {
    if(started){
        end();
        started = false;
        $('#inputSpeed').val(speed);
    }     
});

