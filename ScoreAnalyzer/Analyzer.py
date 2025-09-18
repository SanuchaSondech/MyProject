import csv

# อ่านข้อมูลจาก CSV
students = []

with open("DataScore.csv", "r", encoding="utf-8") as file:
    reader = csv.DictReader(file)
    # ดูค่าทีละแถว
    for row in reader:

        name = row["Name"]
        reading = float(row["Reading"])
        listening = float(row["Listening"])
        grammar = float(row["Grammar"])
        
        totalScore = reading + listening + grammar # คะแนนรวม
        max_score = 150 # คะแนนเต็ม

        # ตรวจสอบผ่าน/ไม่ผ่าน
        if totalScore >= max_score/2 :
            results = "ผ่าน"
        else :
            results = "ไม่ผ่าน"
        
        #ใส่ค่าในลิสต์ students
        students.append({
            "name": name,
            "reading": reading,
            "listening": listening,
            "grammar": grammar,
            "total": totalScore,
            "max_score": max_score,
            "results": results
        })

print("ผลคะแนนนักเรียนแต่ละคน:")
for s in students:
    print("%s คะแนนรวม %.1f/%d %s" % (s['name'], s['total'], s['max_score'], s['results']))

# สร้างฟังก์ชั่นคิดค่าเฉลี่ยของแต่ละวิชา
def calculateAverage(students, subject):

    sum = 0
    for s in students :
        sum = sum + s[subject]
    avg = sum/len(students)
    return avg

# สร้างฟังก์ชั่นหาค่าเฉลี่ยจำนวนคนที่ผ่านและไม่ผ่าน
def resultsAverage(students, subject = "results") :
    resulPassed = 0
    resulFailed = 0
    for s in students :
        if s['results'] == "ผ่าน" :
            resulPassed = resulPassed + 1
        else :
            resulFailed = resulFailed + 1
    avgResulPassed = (resulPassed / (resulPassed+resulFailed)) * 100
    return resulPassed, resulFailed, avgResulPassed      

# เรียกใช้ ฟังก์ชั่น
avgReading = calculateAverage(students,"reading")
avgListening = calculateAverage(students, "listening")
avgGrammar = calculateAverage(students, "grammar")
passed, failed, avgPassed = resultsAverage(students)

print("\nค่าเฉลี่ยแต่ละวิชา:")
print("Reading: %.2f คะแนน" % avgReading)
print("Listening: %.2f คะแนน" % avgListening)
print("Grammar: %.2f คะแนน" % avgGrammar)
print("มีผู้สอบผ่าน %d คน ไม่ผ่าน %d คน \nทั้งสองสอบผ่าน %.2f%%" % (passed, failed, avgPassed))
