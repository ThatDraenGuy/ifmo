import os
import subprocess
import time
import threading
import matplotlib.pyplot as plt
import operator
from functools import reduce

ORIGINAL_PATH = os.path.dirname(os.path.abspath(__file__))

bogops_indexes = []
bogops = []
bogops_per_sec = []

def get_process_output_lines(process: list[str]):
  return subprocess.check_output(process).decode("utf-8").split("\n")

def perform_stress(stress: list[str]):
    res = subprocess.check_output(stress, stderr=subprocess.STDOUT).decode("utf-8").split("\n")[5].split()
    global bogops, bogops_per_sec
    bogops.append(int(res[4]))
    bogops_per_sec.append(float(res[8]))
    print("bogo ops: " + res[4] + "; bogo ops/s: " + res[8])

def test_cpu(method: str, worker_num: int):
    os.chdir(ORIGINAL_PATH)
    os.chdir("cpu")

    measure_time = 20

    values = [None] * worker_num;
    for i in range(0, worker_num):
        values[i] = []
    times = []

    # process = subprocess.Popen(["stress-ng", "--cpu-method", method, "--cpu", "1", "--timeout", str(measure_time + 10) + "s", "--metrics"], shell=False)
    stress = threading.Thread(target=perform_stress, args=(["stress-ng", "--cpu-method", method, "--cpu", str(worker_num), "--timeout", str(measure_time + 10) + "s", "--metrics"],))
    stress.start()
    time.sleep(1)
    pids = get_process_output_lines(["pgrep", "stress-ng"])
    pids.pop(0)
    pids.pop()
    pid_args = list(reduce(operator.concat, map(lambda pid: ("-p", pid), pids)))

    start_time = time.time()

    while time.time() - start_time < measure_time:
        res = get_process_output_lines(["top", "-b"] + pid_args + ["-n", "1"])[7::]
        res.pop()
        res = list(map(lambda top_res: float(top_res.split()[8].replace(',', '.')), res))
        for i in range(0, worker_num):
            values[i].append(res[i])

        times.append(time.time() - start_time)

    stress.join()
    # print(stdout.decode("utf-8"))

    plt.clf()

    for i in range(0, worker_num):
        plt.plot(times, values[i])

    plt.title("CPU: " + method + "; workers num = " + str(worker_num))
    plt.xlabel("Time (seconds)")
    plt.ylabel("CPU (%)")
    plt.ylim(0, 110)
    plt.savefig(method + "-" + str(worker_num) + ".png")  


def test_method(method_name: str, max_workers_num: int):
    global bogops_indexes, bogops, bogops_per_sec
    bogops_indexes.clear()
    bogops_indexes += list(range(1, max_workers_num))
    bogops.clear()
    bogops_per_sec.clear()
    for i in range (1, max_workers_num):
        test_cpu(method_name, i)
    
    plt.clf()
    plt.plot(bogops_indexes, bogops)
    plt.title("Bogo ops for " + method_name + " method")
    plt.xlabel("Workers num")
    plt.ylabel("Bogo ops")
    plt.savefig(method_name + "-bogops.png")  

    plt.clf()
    plt.plot(bogops_indexes, bogops_per_sec)
    plt.title("Bogo ops/sec for " + method_name + " method")
    plt.xlabel("Workers num")
    plt.ylabel("Bogo ops/sec")
    plt.savefig(method_name + "-bogops-per-sec.png")  

    
test_method("gray", 17)
test_method("int128decimal128", 17)