
var startTime, endTime, speed
var started = false;

function start() {
  startTime = new Date();
  speed = 0;
};

function end() {
    if(started){
        endTime = new Date();
        speed = endTime - startTime;
        console.log("Speed: "+speed);
        started = false;
        $('#speed').val(speed);
        $('#debug-speed').text('Speed: '+speed+' ms');
    }
}

$('#password').keydown(function() {
    if(!started){
        start();
        started = true;
        console.log("Started: "+startTime);
    }    
    console.log('keypress');
});

$('#password').on('focusout', function() {
    end();
});

$('form').on('submit', function() {
   end();
});

