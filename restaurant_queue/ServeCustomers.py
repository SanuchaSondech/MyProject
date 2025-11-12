import csv
import os

# ไฟล์ที่ใช้เก็บ
CUSTOMERS_FILE = "Customers.csv"
HISTORY_FILE = "history.csv"

# Queue + list
class Queue:
    def __init__(self):
        self.items = []

    # เพิ่มลูกค้าเข้า
    def enqueue(self, customer):
        if customer["VIP"]:
            # last_vip_index ทำหน้าที่เป็น pointer ชี้ไปยัง vip ในคิว
            last_vip_index = -1
            # loop รับค่า ตำแหน่ง i และค่า v
            for i, v in enumerate(self.items):
                # เมื่อเจอ vip ให้ pointer ชี้ที่ตัวนั้น
                if v["VIP"]:
                    last_vip_index = i
            # ถ้าไม่เจอเลย ให้ลูกค้าคนนี้อยู่คนแรกของคิว
            if last_vip_index == -1:
                self.items.insert(0, customer)
            else:
            # ถ้าเจอให้ใส่ลูกค้าคนนี้ไปยังตำแหน่ง last_vip_index + 1
                self.items.insert(last_vip_index + 1, customer)
        else:
            self.items.append(customer)

    # เอาออกจากหัว queue
    def dequeue(self):
        if self.items:
            return self.items.pop(0)
        return None

    # ดูหัว queue
    def peek(self):
        if self.items:
            return self.items[0]
        return None

    # ตรวจสอบว่าว่างหรือไม่
    def is_empty(self):
        return len(self.items) == 0

    # คืนค่าทั้ง queue
    def all_items(self):
        return self.items

# ตรวจสอบ CUSTOMERS_FILE ถ้าไม่มีให้สร้างและเขียน header
if not os.path.exists(CUSTOMERS_FILE):
    with open(CUSTOMERS_FILE, "w", encoding="utf-8", newline="") as file:
        writer = csv.writer(file)
        writer.writerow(["ID", "Menu", "VIP"])
        print(f"File '{CUSTOMERS_FILE}' created with header.\n")

# ตรวจสอบ HISTORY_FILE ถ้าไม่มีให้สร้างและเขียน header
if not os.path.exists(HISTORY_FILE):
    with open(HISTORY_FILE, "w", encoding="utf-8", newline="") as file:
        writer = csv.writer(file)
        writer.writerow(["ID", "Menu", "VIP"])
        print(f"File '{HISTORY_FILE}' created with header.\n")

# โหลดลูกค้าจาก CSV
def load_customers(CUSTOMERS_FILE):
    q = Queue()
    
    with open(CUSTOMERS_FILE, "r", encoding="utf-8") as file:
        reader = csv.DictReader(file)
        for row in reader: # for  i : array{}
            customer = {
            "ID": row["ID"].strip(),
            "Menu": row["Menu"].strip(),
            "VIP": row["VIP"].strip().lower() == "true"
            }
            q.enqueue(customer)
    return q

# บันทึก queue กลับ CSV
def save_customers(CUSTOMERS_FILE, queue):
    with open(CUSTOMERS_FILE, "w", encoding="utf-8", newline="") as file:
        writer = csv.writer(file)
        writer.writerow(["ID", "Menu", "VIP"])
        for c in queue.items:
            writer.writerow([c["ID"], c["Menu"], c["VIP"]])



# บันทึกลูกค้าที่ serve แล้วลง history.csv
def append_history(HISTORY_FILE, customer):
    with open(HISTORY_FILE, "a", encoding="utf-8", newline="") as file:
        writer = csv.writer(file)
        writer.writerow([customer["ID"], customer["Menu"], customer["VIP"]])

# โหลดลูกค้า
queue = load_customers(CUSTOMERS_FILE)

print("Customer service program running. Type 'serve' to serve next customer, 'stop' to stop.")

# รันโปรแกรม
while True:
    # ถ้าคิวไม่ว่าง
    if not queue.is_empty():
        next_customer = queue.peek()
        print(f"\nNext customer is ID: {next_customer['ID']}, Menu: {next_customer['Menu']}, VIP: {next_customer['VIP']}")
    # ถ้าคิวว่าง
    else:
        print("\nQueue is empty. No customers waiting.")

    # รับคำสั่ง
    command = input("Enter command (serve/stop): ").strip().lower()

    if command == "serve":
        if not queue.is_empty():
            served = queue.dequeue()
            print(f"Serving customer ID: {served['ID']}")
            append_history(HISTORY_FILE, served)
        else:
            print("No customers to serve.")
    elif command == "stop":
        print("Stopping program and saving current queue...")
        save_customers(CUSTOMERS_FILE, queue)
        break
    else:
        print("Invalid command. Use 'serve' or 'stop'.")