import math
import os
import subprocess
import time
import threading
import matplotlib.pyplot as plt
import operator
from functools import reduce

ORIGINAL_PATH = os.path.dirname(os.path.abspath(__file__))

def get_process_output_lines(process: list[str]):
  return subprocess.check_output(process).decode("utf-8").split("\n")

def perform_stress(stress: list[str]):
    subprocess.check_output(stress)
    return

def test_network(method: str, worker_num: int):
    os.chdir(ORIGINAL_PATH)
    os.chdir("network")

    measure_time = 60

    received = []
    sent = []
    times = []

    stress = threading.Thread(target=perform_stress, args=(["sudo", "stress-ng", "--" + method, str(worker_num), "--timeout", str(measure_time + 10) + "s", "--metrics"],))
    stress.start()
    time.sleep(1)

    start_time = time.time()

    last_second = 0

    res_lines = get_process_output_lines(["ip", "-s", "link", "show", "lo"])
    last_received_val = int(res_lines[3].split()[1])
    last_sent_val = int(res_lines[5].split()[1])

    while time.time() - start_time < measure_time:
        second = math.floor(time.time() - start_time)
        if (second == last_second):
            continue
        last_second = second
        
        res_lines = get_process_output_lines(["ip", "-s", "link", "show", "lo"])
        received_val = int(res_lines[3].split()[1])
        sent_val = int(res_lines[5].split()[1])

        received.append(received_val - last_received_val)
        sent.append(sent_val - last_sent_val)

        last_received_val = received_val
        last_sent_val = sent_val

        times.append(last_second)

    stress.join()

    plt.clf()
    plt.plot(times, received)
    plt.title("Network: " + method + "; workers num = " + str(worker_num))
    plt.xlabel("Time (seconds)")
    plt.ylabel("Received packets per second")
    plt.ylim(bottom=0)
    plt.savefig(method + "-" + str(worker_num) + "-received" + ".png")  

    plt.clf()
    plt.plot(times, sent)
    plt.title("Network: " + method + "; workers num = " + str(worker_num))
    plt.xlabel("Time (seconds)")
    plt.ylabel("Sent packets per second")
    plt.ylim(bottom=0)
    plt.savefig(method + "-" + str(worker_num) + "-sent" + ".png")  


def test_method(method_name: str, start: int, end: int):
    for i in range (start, end):
        test_network(method_name, i)
    
# test_method("netlink-proc", 9)
test_method("dccp", 9, 17)
# test_method("test", 9)
