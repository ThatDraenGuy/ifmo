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
    res = subprocess.check_output(stress).decode("utf-8").split("\n").pop().split()
    return

def test_pipe(method: str, start_worker_num: int, end_worker_num: int):
    os.chdir(ORIGINAL_PATH)
    os.chdir("pipe")

    measure_time = 20

    avg_values = []
    sum_values = []
    workers = []

    
    for i in range(start_worker_num, end_worker_num):
        res = subprocess.check_output(["stress-ng", "--" + method, str(i), "--timeout", str(measure_time + 10) + "s", "--metrics"], stderr=subprocess.STDOUT).decode("utf-8").split("\n")[-2].split()[4]
        print(res)
        avg_values.append(float(res))
        sum_values.append(float(res) * i)
        workers.append(i)


    plt.clf()
    plt.plot(workers, avg_values)
    plt.title("Pipe: " + method)
    plt.xlabel("Workers num")
    plt.ylabel("Context switches per second (avg per worker)")
    plt.ylim(bottom=0)
    plt.savefig(method + "-avg" + ".png")  

    plt.clf()
    plt.plot(workers, sum_values)
    plt.title("Pipe: " + method)
    plt.xlabel("Workers num")
    plt.ylabel("Context switches per second")
    plt.ylim(bottom=0)
    plt.savefig(method + "-sum" + ".png")  

    
test_pipe("pipeherd", 1, 17)
