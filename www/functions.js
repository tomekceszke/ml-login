
var startTime, endTime
var started = false;

function start() {
  startTime = new Date();
};

function end() {
  endTime = new Date();
  var timeDiff = endTime - startTime; //in ms
  console.log("Time diff: "+timeDiff);
}

$('#password').keydown(function() {
    if(!started){
        start();
        started = true;
        console.log(startTime);
    }
    // else {
    //     end();
    // }
    console.log('keypress');
});

$('form').on('submit', function() {
    if(started){
        end();
        started = false;
        $.post('superman', { field1: "hello", field2 : "hello2"}, 
        function(returnedData){
         console.log(returnedData);
        });
    }
});

