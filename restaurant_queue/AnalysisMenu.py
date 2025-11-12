import csv
import os

# ไฟล์ที่ใช้เก็บ
HISTORY_FILE = "history.csv"

# ราคาแต่ละเมนู
menu_price = {
    "pad thai": 50,
    "green curry": 70,
    "tom yum soup": 80,
    "fried rice": 55,
    "papaya salad": 45,
    "massaman curry": 75,
    "basil chicken": 60,
    "chicken satay": 50,
    "noodle soup": 55,
    "mango sticky rice": 65
}

# ตรวจสอบ HISTORY_FILE ถ้าไม่มีให้สร้างและเขียน header
if not os.path.exists(HISTORY_FILE):
    with open(HISTORY_FILE, "w", encoding="utf-8", newline="") as file:
        writer = csv.writer(file)
        writer.writerow(["ID", "Menu", "VIP"])
        print(f"File '{HISTORY_FILE}' created with header.\n")

# สร้างตัวแปลมาเก็บข้อมูลของ เมนูและจำนวน ที่ถูกสั่ง
menu_count = {
    "pad thai": 0,
    "green curry": 0,
    "tom yum soup": 0,
    "fried rice": 0,
    "papaya salad": 0,
    "massaman curry": 0,
    "basil chicken": 0,
    "chicken satay": 0,
    "noodle soup": 0,
    "mango sticky rice": 0
}

sum_nemu = 0

# อ่านไฟล์ HISTORY_FILE
with open(HISTORY_FILE, "r", encoding="utf-8") as file:
    reader = csv.DictReader(file)
    for row in reader:
        menu = row["Menu"].strip().lower()
        # นับจำนวนการสั่งเมนู
        if menu in menu_count:  # ตรวจสอบว่าเมนูมีอยู่ใน dictionary
            menu_count[menu] += 1
            sum_nemu = sum_nemu+1

totol = 0
# แสดงผลสรุปจำนวนการสั่งแต่ละเมนู
print("Summary of menu orders:")
for menu, count in menu_count.items():
    price = count * menu_price[menu]
    totol = totol + price
    print(f"{menu}: {count} orders : totol {price} Bath")

# รวมทั้งหมด
print(f"totol {sum_nemu} menu : totol {totol} Bath")