import math
from khi_table import *

def dispersion_and_avg(arr):
    xi = 0
    average = round(sum(arr) / len(arr), 6)
    print(f"\nAverage of the generator: {average}")
    for i in arr:
        xi += (i - average)**2
    dispersion = xi / (len(arr) - 1)
    print(f"Dispersion of the generator: {dispersion}")


def theory_exp(generated_table):
    lyambda = 1/(sum(generated_table) / len(generated_table))
    max_r = max(generated_table)
    min_r = min(generated_table)
    k = 20
    h = round((max_r - min_r) / k, 6)
    old_h = h
    intervals_list = list()
    expected_list = list()
    for i in range(k):
        if i == 0:
            intervals_list.append(0.00)
        else:
            intervals_list.append(h)
            h += old_h
    for v in range(k):
        if k - v >= 2:
            e_1 = math.exp(1) ** (-lyambda * intervals_list[v])
            e_2 = math.exp(1)**(-lyambda*intervals_list[v+1])
            expected_list.append(e_1 - e_2)
        else:
            e = math.exp(1) ** (-lyambda * intervals_list[v])
            expected_list.append(e)
    return expected_list


def khi(expected_values, internal_values):
    khi1 = 0
    for i in range(len(expected_values)):
        expected = 10000 * expected_values[i]
        khi1 += (internal_values[i] - expected)**2 / expected
    khi2 = find_table_khi(len(expected_values))
    return khi1, khi2

