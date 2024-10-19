//Rock paper scissors, randomly return rock, paper, or scissors

//Grabbing random word from my array
function getComputerChoice(){
    const list = ["rock", "paper", "scissors"];
    //Math.random() returns a number between 0 and 1, so Math.Floor() rounds down. And multiply it by the length of the array
    const random = Math.floor(Math.random() * list.length);
    return list[random];
}

//Using implicit returns and an arrow function for this simple function that takes input
const getHumanChoice = () => prompt("Input: rock, paper, or scissors");




function playGame(){
    let humanScore = 0;
    let computerScore = 0;
    let numberOfRounds = 5;

    function playRound (humanChoice, computerChoice) {
        humanChoice = humanChoice.toLowerCase();
    
        if (humanChoice === computerChoice){
            console.log("It's a tie!");
        }
        else if (humanChoice === 'rock' && computerChoice === 'scissors' || humanChoice === 'paper' && computerChoice === 'rock' || humanChoice === 'scissors' && computerChoice === 'paper'){
            humanScore++;
        }
        else{
            computerScore++;
        }
    }

    for (let currentRound = 0; currentRound <= numberOfRounds; currentRound++){
        playRound(getHumanChoice(), getComputerChoice());
        console.log(`Human Score: ${humanScore}, Computer Score: ${computerScore}`);
    }


    if(computerScore > humanScore){
        console.log(`Computer Wins!`);
    }
    else if(computerScore === humanScore){
        console.log(`It's a tie!`);
    }
    else{
        console.log(`Human Won!`);
    }
}




playGame();