const transactType=document.querySelector("#transact-type");

const paymentCard=document.querySelector(".payment-card");
const transferCard=document.querySelector(".transfer-card");
const depositCard=document.querySelector(".deposit-card");
const withDrawCard=document.querySelector(".withdraw-card")

//console.log(transactType);

transactType.addEventListener("change",()=>{
  switch(transactType.value){
	  				
    case "payment":{
      paymentCard.nextElementSibling.style.display="none";
      depositCard.style.display="none";
      withDrawCard.style.display="none";
      paymentCard.style.display="block";
      break;
    };

    case "transfer":{
      transferCard.previousElementSibling.style.display="none";
      transferCard.nextElementSibling.style.display="none";
      withDrawCard.style.display="none";
      transferCard.style.display="block";
      break;
    }

    case "deposit":{
      depositCard.previousElementSibling.style.display="none";
      depositCard.nextElementSibling.style.display="none";
      paymentCard.style.display="none";
      depositCard.style.display="block";
      break;
    }

    case "withdraw":{
      withDrawCard.previousElementSibling.style.display="none"; 
      transferCard.style.display="none";
      paymentCard.style.display="none";
      withDrawCard.style.display="block";
      break;
    }

    /*default:{
      paymentCard.style.display="none";
      transferCard.style.display="none";
      depositCard.style.display="none";
      withDrawCard.style.display="none";
    }*/
    
  };
});