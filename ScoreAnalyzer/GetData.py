import csv

# สร้างฟังก์ชั่นตรวจสอบคะแนน
def checkScore(subject):
    while True:
        try:
            score = int(input(f"คะแนน {subject} : "))
            if 0 <= score <= 50:
                return score
            else:
                print("คะแนนต้องอยู่ระหว่าง 0-50 กรุณาใส่ใหม่")
        except ValueError:
            print("กรุณาใส่ตัวเลขเท่านั้น")

with open("DataScore.csv", "a", encoding="utf-8", newline="") as file:
    writer = csv.writer(file)
    print("โปรแกรมเริ่มทำงาน พิมพ์ exit บันทึก")
    
    while True:
        try :
            name = input("ชื่อนักเรียน : ")
            if name.lower() == "exit": 
                print("โปรแกรมสิ้นสุดแล้ว")
                break
            if name.lower() == "clear":
                with open("DataScore.csv", "w", encoding="utf-8", newline="") as file:
                    clearWriter = csv.writer(file)
                    clearWriter.writerow(["Name","Reading","Listening","Grammar"])    
                    
                print("ลบข้อมูลทั้งหมดเรียบร้อยแล้ว")
                continue 
            
            reading = checkScore("Reading")
            listening = checkScore("Listening")
            grammar = checkScore("Grammar")

        except ValueError :
            break
            print("โปรแกรมสิ้นสุดแล้ว")

        # ใส่ข้อมูลลงไปใน DataScore.csv
        writer.writerow([name, reading, listening, grammar])

# แสดงข้อมูลที่บันทึก
with open("DataScore.csv", "r", encoding="utf-8") as file:   
    print("บันทึกสำเร็จ")
    reader = csv.reader(file)
    for data in reader:
        print(data)