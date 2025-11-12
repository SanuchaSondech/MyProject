import csv
import os

# ตรวจสอบ vip
def check_vip():
    while True:
        vip = input("VIP? (y/n): ").strip().lower()
        if vip in ["y", "n"]:
            return vip == "y" #T or F
        else:
            print("Enter 'y' for vip and 'n' for not vip")

# รายการเมนูในร้าน
my_menu = [
    "pad thai",             
    "green curry",          
    "tom yum soup",         
    "fried rice",          
    "papaya salad",        
    "massaman curry",       
    "basil chicken",       
    "chicken satay",      
    "noodle soup",        
    "mango sticky rice"   
]

# ตรวจสอบเมนู
def check_menu():
    while True:
        menu = input("Menu: ").strip().lower()
        if menu in my_menu:
            return menu
        else:
            print("Don't have this menu. Please choose again.")

# ตรวจสอบไฟล์ ถ้าไม่มีให้สร้างและเขียน header
if not os.path.exists("Customers.csv"):
    with open("Customers.csv", "w", encoding="utf-8", newline="") as file:
        writer = csv.writer(file)
        writer.writerow(["ID", "Menu", "VIP"])
        print(f"File '{"Customers.csv"}' created with header.\n")

# เปิดไฟล์ CSV เพื่อเขียนข้อมูล
with open("Customers.csv", "a", encoding="utf-8", newline="") as file:
    writer = csv.writer(file)

    print("Program is runing enter 'exit' to close program")

    print("We have",end=" ")
    for i in my_menu:
        print(f"{i}, ", end="")
    print("")
    
    # while True: เพื่อลูปนำเข้าข้อมููล
    while True:
        customers_id = input("Customer ID: ").strip()
        if customers_id.lower() == "exit":
            print("All data is save")
            break

        

        menu = check_menu()

        vip = check_vip()

        # บันทึกไว้ใน CSV
        writer.writerow([customers_id, menu, vip])
        print(f"Preparing menu for ID : {customers_id}. Please wait a moment.")
