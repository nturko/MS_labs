import random as rand
import numpy as np
import math
import matplotlib.pyplot as plt
from extra_functions import *


class Generator(object):

    def generator_1(self, lyambda, count_gen):
        print("\n\n*** Generator-1 *** ")
        generated_table = list()
        for i in range(count_gen):
            zetta = round(rand.uniform(0, 1), 6)
            generated_table.append(round(- (math.log(zetta))/lyambda, 6))
        dispersion_and_avg(generated_table)
        return generated_table

    def generator_2(self, sigma, myu, count_gen):
        print("\n\n*** Generator-2 *** ")
        generated_table = list()
        count_xi = 12
        for i in range(count_gen):
            generated_xi = 0
            for k in range(count_xi):
                generated_xi += round(rand.uniform(0, 1), 6)
            xi = generated_xi - 6
            generated_table.append(round(sigma * xi + myu, 6))
        dispersion_and_avg(generated_table)
        return generated_table

    def generator_3(self, count_gen):
        print("\n\n*** Generator-3 *** ")
        generated_table = list()
        b = 5**13
        c = 2**31
        xi = round(rand.uniform(0, 1), 6)
        z = (b * xi % c)
        for i in range(count_gen):
            z = b*z % c
            generated_table.append(round(z / c, 6))
        dispersion_and_avg(generated_table)
        return generated_table

    def interval_table(self, generated_table, k):
        print("\n--- INTERVALS FOR GENERATOR ---")
        print("Interval      Values")
        max_r = max(generated_table)
        min_r = min(generated_table)
        h = round((max_r - min_r) / k, 6)
        np_generated_table = np.array(generated_table)
        intervals_list = dict()
        old_h = h
        for i in range(k):
            if i == 0:
                intervals_list[f"0.00 - {round(h, 3)}"] = np.compress((0 < np_generated_table) & (np_generated_table < h), np_generated_table).size
            else:
                intervals_list[f"{round(h, 3)} - {round(h+old_h, 3)}"] = np.compress((h < np_generated_table) & (np_generated_table < h+old_h), np_generated_table).size
                h += old_h
        for y, v in intervals_list.items():
            print(f'{y}  :  {v}')
        return intervals_list

    def anylyze(self, generated_table1, interval_dict1):
        expected_values1 = theory_exp(generated_table1)
        interval_values1 = list()
        for key in interval_dict1.keys():
            interval_values1.append(interval_dict1[key])
        hkiv_1_get, hkiv_1_expected = khi(expected_values1, interval_values1)
        print(f"Xi-get from generator_1:  {hkiv_1_get}")
        print(f"Xi-expected for generator_1: {hkiv_1_expected}")
        print("Is Xi-expected more-than Xi-get from generator_1:", hkiv_1_get < hkiv_1_expected)

    def histogram(self, interval_dict):
        plt.bar(list(interval_dict.keys()), interval_dict.values(), color='g', edgecolor='black')
        plt.xticks(rotation='vertical')
        plt.show()
