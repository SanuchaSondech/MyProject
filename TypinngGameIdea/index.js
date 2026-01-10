///_________ส่วนตัวละคร__________///
const characterEl = document.querySelector("#character");
let characterX = characterEl.offsetLeft; // ตำแหน่งเริ่มต้นตัวละคร

// อนิเมชั่นตัวละครหายใจ
const characterFrames = [
    "img/characterBobby/characterBobby1.png",
    "img/characterBobby/characterBobby2.png"
];

// ตัวชี้เฟรมหายใจ
let currentCharacterFrame = 0;

// เซ็ตลูปอนิเมชั่นตัวละครหายใจ
const characterAnimationInterval = setInterval(() => {
    if (gameState !== "playing") return; // บังคับยกเลิกฟังชั่น

    // สลับรูป
    currentCharacterFrame = currentCharacterFrame === 0 ? 1 : 0;
    characterEl.querySelector("img").src = characterFrames[currentCharacterFrame];

}, 500);

// อนิเมชั่นตัวละครยิง
const characterShootFrames = [
    "img/characterBobby/characterBobby3.png",
    "img/characterBobby/characterBobby4.png"
];

// ฟังก์ชั่นยิง
function characterShoot(num) {
    characterEl.querySelector("img").src = characterShootFrames[num];
}


///_________ส่วนซอมบี้__________///
const zombieEl = document.querySelector(".zombie");
let zombieX = zombieEl.offsetLeft; // ตำแหน่งเริ่มต้นซอมบี้
const speed = 20; // px/s <<<< ปรับตรงนี้
const interval = 100; // ms
const zombieStep = speed * (interval / 1000); //px=px/s*s
let zombieInterval = null; // ตัวแปรเก็บ interval ของซอมบี้

// เปลี่ยนตำแหน่ง
function updatePosition(Obj, x) {
    Obj.style.left = x + "px";
}

// อนิเมชันรูปซอมบี้
const zombieFrames = [
    "img/zombie/zombie1.png",
    "img/zombie/zombie2.png"
];
// ตัวชี้เฟรม
let currentZombieFrame = 0;

// ซอมบี้เดิน
function moveZombie() {

    if (gameState !== "playing") return; // บังคับยกเลิกฟังชั่น

    // ตรสจสอบการชน
    if (isGameOver()) {
        gameState = "gameover";
        alert("คุณแพ้!");
        clearInterval(zombieInterval); // หยุดซอมบี้
    } else {
        zombieX = zombieX - zombieStep;
        updatePosition(zombieEl, zombieX);

        //สลับรูป
        currentZombieFrame = currentZombieFrame === 0 ? 1 : 0;
        zombieEl.querySelector("img").src = zombieFrames[currentZombieFrame];
    }


}

function zombieHitPanelEdge() {
    const zombieRect = zombieEl.getBoundingClientRect();
    const panalRect = panalEl.getBoundingClientRect();

    // ชนขอบขวาของ panal
    return zombieRect.right >= panalRect.right;
}


// ซอมบี้ชนแล้วแพ้
function isGameOver() {
    const characterRect = characterEl.getBoundingClientRect();
    const zombieRect = zombieEl.getBoundingClientRect();

    return characterRect.right >= zombieRect.left + 80;
}


///_________ส่วนเกม__________///
// สถานะเกม
let gameState = "wait"; // playing | gameover | win

// พื้นที่ panal
const panalEl = document.querySelector("#panal");

let level = 0; // ด่าน

//ปุ่มเริ่มเกม
function gameStart() {
    if (gameState == "playing") return; // บังคับยกเลิกฟังชั่น
    gameState = "playing";
    zombieInterval = setInterval(moveZombie, interval); // เริ่มซอมบี้เดิน
    showPage(level, page); // แสดงหน้า
    const spans = targetTextEl.querySelectorAll("span"); // เลือก span ทั้งหมดที่ส้ราง
    console.log(spans);
}

// ปุ่มรีเซ็ตเกม
function gameReset() {
    gameState = "wait";
    clearInterval(zombieInterval); // หยุดซอมบี้
    zombieX = 900; // ตำแหน่งเริ่มต้นซอมบี้
    zombieEl.style.left = zombieX + "px";

    typingText.textContent = ""; // ล้างข้อความที่ผู้ใช้พิมพ์
    userInput.length = 0; // เคลียร์ข้อความผู้ใช้

    targetTextEl.textContent = ""; // ล้างข้อความเป้าหมาย
    targetTextIndex = 0; // รีเซ็ตตำแหน่งคำที่ต้องพิมพ์
    page = 0; // รีเซ็ตหน้า


    scoreCorrect = 0; // รีเซ็ตคะแนนพิมพ์ถูก
    scoreWrong = 0; // รีเซ็ตคะแนนพิมพ์ผิด

    hiddenScoreBoard() // ซ่อนข้อมูลคะแนน
}

// เกมชนะ
function gameWin() {
    gameState = "win"; // playing | gameover | win
    clearInterval(zombieInterval); // หยุดซอมบี้
    showScoreBoard();
    showGameresult()
}

// แสดงข้อมูลคะแนน
function showScoreBoard(){
    const scoreBoardEl = document.querySelector("#scoreBoard");
    scoreBoardEl.classList.remove("hidden");
    scoreBoardEl.classList.add("show-center");
    // แสดงคะแนน
    const scoreCorrectEl = document.querySelector("#scoreCorrect");
    scoreCorrectEl.textContent = scoreCorrect;
    const scoreWrongEl = document.querySelector("#scoreWrong");
    scoreWrongEl.textContent = scoreWrong;
    
}

// ซ่อนข้อมูลคะแนน
function hiddenScoreBoard(){
    const scoreBoardEl = document.querySelector("#scoreBoard");
    scoreBoardEl.classList.add("hidden");
    scoreBoardEl.classList.remove("show-center");
}

function showGameresult(){

    const resultEl = document.querySelector("#result");
    let sumScore = scoreCorrect + scoreWrong;
    // ผ่านเมื่อพิมพ์ถูก 80% ขึ้นไป
    if ((scoreCorrect / sumScore) >= 0.8) {
        // ผ่าน
        resultEl.textContent = "You Passed!";
    } else {
       // ไม่ผ่าน
        resultEl.textContent = "You Failed!";
    }
}

//__________ส่วนข้อความ__________//
let scoreCorrect = 0; // คะแนนพิมพ์ถูก
let scoreWrong = 0; // คะแนนพิมพ์ผิด
let page = 0; // หน้า
// targetText
// ด่านที่ 1 หน้าที่ 0
const textContentLv1P1 = [
    "static;",
    "int a = 5;",
    "int b = 10;",
    "int sum = a + b;",
    "System.out.println(a);",
    "void;",
    "int a = 5;",
    "int b = 10;",
    "System.out.println(sum);",
    "System.out.println();"
];

// ด่านที่ 1 หน้าที่ 1
const textContentLv1P2 = [
    "if(a > b)",
    "if(a < b)",
    "while(a < 10)",
    "for(int i=0;i<5;i++)",
    "while(b > 0)",
    "if(a > b)",
    "for(int i=0;i<5;i++)",
    "while(a < 10)",
    "System.out.println(i);",
    "if(a == b)"
];

// ด่านที่1
const textContentLv1 = [textContentLv1P1, textContentLv1P2];
// ทุกด่าน
const allTextContent = [textContentLv1];

// ช่องข้อความ
const targetTextEl = document.querySelector("#targetText");

// แสดงข้อความบน HTML
function showText(Element, level, page, targetTextIndex) {
    Element.textContent = allTextContent[level][page][targetTextIndex];
}

// แสดงหน้าข้อความ
function showPage(level, page) {

    // ไม่มีหน้าต่อไป
    if (page >= allTextContent[level].length) {
        gameWin();
        console.log("คุณชนะ");
        return;
    }

    // ล้างข้อความเดิม
    targetTextEl.textContent = "";
    // ดึง aray ข้อความทั้งหมด
    const allText = allTextContent[level][page];

    for (let text of allText) {
        const textDiv = document.createElement("div");
        // เพิ่มคลาส text
        textDiv.classList.add("text", "target-Text", "rounded");

        // แยก text เป็น span
        for (let char of text) {
            const span = document.createElement("span");
            span.textContent = char;
            // เพิ่ม span ลงใน div class="text"
            textDiv.appendChild(span);
        }
        // เพิ่ม div ข้อความลงใน targetText
        targetTextEl.appendChild(textDiv);
    }
}

// ฟังก์ชั่นพิมพ์ถูก
function onTypeCorrect(length) {
    scoreCorrect++;
    characterShoot(0);

    if (!zombieHitPanelEdge()) {
        zombieX += zombieStep + length * 10; // ขยับซอมบี้ถอยหลัง
        updatePosition(zombieEl, zombieX);
    }
}

// ฟังก์ชั่นพิมพ์ผิด
function onTypeWrong() {
    scoreWrong++;
    characterShoot(1);
}

// ตรวจสอบว่าพิมพ์ถูกไหม
const typingText = document.querySelector("#typingText p");
typingText.textContent = ""; // ล้างข้อความที่ผู้ใช้พิมพ์

let targetTextIndex = 0; // ตำแหน่งคำที่ต้องพิมพ์

let userInput = [];// เก็บชุดตัวอักษรที่ผู้ใช้พิมพ์
let userInputIndex = 0; // ตำแหน่งตัวอักษรในคำที่ผู้ใช้พิมพ์

let wrong = 0; // ตัวนับคำผิด

document.addEventListener("keydown", function (event) {
    if (gameState !== "playing") return; // บังคับยกเลิกฟังชั่น
    
    // เมื่อกด Enter
    if (event.key === "Enter") {

        userInputIndex = 0; // รีเซ็ตตัวชี้ตำแหน่งตัวอักษรผู้ใช้
        wrong = 0; // รีเซ็ตตัวนับคำผิด

        const targetTextDivs = targetTextEl.querySelectorAll(".text");

        let targetTextDiv = targetTextDivs[targetTextIndex]; // คำที่ต้องพิมพ์

        const targetSpans = targetTextDiv.querySelectorAll("span"); // ตัวอักษรในคำนั้น
        
        // ตรวจสอบตัวอักษรที่พิมพ์
        for (let targetSpan of targetSpans) {
            if (targetSpan.textContent === userInput[userInputIndex]) {
                targetSpan.classList.add("correct");
            } else {
                targetSpan.classList.add("wrong");
                wrong++;
            }
            userInputIndex++; // เลื่อนไปตัวอักษรถัดไป
        }

        // พิมพ์เกิน
        if (userInput.length > targetSpans.length) {
            wrong += userInput.length - targetSpans.length;
        }

        // คำถูกต้องหรือไม่
        if (wrong === 0){
            onTypeCorrect(userInput.length); 
        } else{
            onTypeWrong();
        }

        userInput = [] // เคลียร์ข้อความ
        typingText.textContent = userInput.join(""); // แสดงข้อความว่าง
        targetTextIndex++; // เลื่อนไปคำถัดไป

        // เปลี่ยนหน้า
        if (targetTextIndex >= targetTextDivs.length) {
            page++;
            targetTextIndex = 0; // รีเซ็ตตำแหน่งคำที่ต้องพิมพ์
            showPage(level, page);
        }
        
    } else if (event.key === "Backspace") {
        // Backspace
        if (userInput.length === 0) return; // ว่างเปล่า
        userInput.pop(); // ลบตัวอักษรตัวสุดท้าย
        typingText.textContent = userInput.join("");
    }else if (event.key.length === 1){
        // พิมพ์ปกติ
        const char = event.key; // ตัวอักษรที่พิมพ์
        userInput.push(char);
        typingText.textContent = userInput.join("");
    }

});
