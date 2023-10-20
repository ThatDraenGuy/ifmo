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

def test_io(method: str, worker_num: int, process_per_worker_num: int, measure_time: int):
    os.chdir(ORIGINAL_PATH)
    os.chdir('io')


    process_num = worker_num * process_per_worker_num

    reads = [None] * process_num
    writes = [None] * process_num
    for i in range(0, process_num):
        reads[i] = []
        writes[i] = []
    times = []

    stress_process = subprocess.Popen(['stress-ng', '--' + method, str(worker_num), '--timeout', str(measure_time + 10)])
    time.sleep(1)

    pids = get_process_output_lines(["pgrep", "stress-ng"])
    pids.pop(0)
    pids.pop()
    pid_args = ','.join(pids)

    start_time = time.time()


    last_second = 0
    while time.time() - start_time < measure_time:
        second = math.floor(time.time() - start_time)
        if (second == last_second):
            continue

        last_second = second
        res = get_process_output_lines(['pidstat', '-d', '-p', pid_args])[3::]

        for i in range(0, process_num):
            reads[i].append(float(res[i].split()[3].replace(',', '.')))
            writes[i].append(float(res[i].split()[4].replace(',', '.')))
        times.append(last_second)

    stress_process.communicate()

    plt.clf()
    for i in range(0, process_num):
        plt.plot(times, reads[i])
    plt.title("IO: " + method + '-read' + "; workers num = " + str(worker_num))
    plt.xlabel("Time (seconds)")
    plt.ylabel("kB_read/s")
    plt.savefig(method + '-read' + "-" + str(worker_num) + "-" + str(measure_time) + "s" + ".png")

    plt.clf()
    for i in range(0, process_num):
        plt.plot(times, writes[i])
    plt.title("IO: " + method + '-write' + "; workers num = " + str(worker_num))
    plt.xlabel("Time (seconds)")
    plt.ylabel("kB_write/s")
    plt.savefig(method + '-write' + "-" + str(worker_num) + "-" + str(measure_time) + "s" + ".png")
    


test_io('iomix', 32, 19, 600)
# test_io('ioprio', 4, 1, 600)