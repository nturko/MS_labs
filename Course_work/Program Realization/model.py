from process import *
from create import *
import pandas as pd
col_name = ['Avg queue', 'Avg load', 'Avg task rate']


class Model(object):
    general_mean_load = 0
    general_mean_queue = 0
    Result_table = pd.DataFrame(columns=col_name)

    def __init__(self, elements_list):
        self.element_list = elements_list
        self.t_next = 0
        self.event = 0
        self.t_curr = 0
        self.stable = []
        self.general_mean_load, self.general_mean_queue = 0.0, 0.0

    def simulate(self, time, flag_info, epsilon=None):
        while self.t_curr < time:
            if self.t_curr > 0.0:
                p1 = self.element_list[1].mean_load / self.t_curr
                p2 = self.element_list[2].mean_load / self.t_curr
                p3 = self.element_list[3].mean_load / self.t_curr

            else:
                p1 = self.element_list[1].mean_load
                p2 = self.element_list[2].mean_load
                p3 = self.element_list[3].mean_load

            avg_load_start = (p1 + p2 + p3) / 3

            self.t_next = np.inf
            for element in self.element_list:
                t_next_val = np.min(element.t_next)
                if t_next_val < self.t_next:
                    self.t_next = t_next_val
                    self.event = element.id_element

            if flag_info is True:
                print("------------------------------")
                print(f"Event {self.element_list[self.event].name}, time= {self.t_next}")
                print("------------------------------")
            for element in self.element_list:
                element.statistics(self.t_next - self.t_curr)

            self.t_curr = self.t_next
            for element in self.element_list:
                element.t_curr = self.t_curr

            self.element_list[self.event].out_act()
            for element in self.element_list:
                if self.t_curr in element.t_next:
                    element.out_act()

            if epsilon is not None:
                if self.t_curr > 0.0:
                    p1 = self.element_list[1].mean_load / self.t_curr
                    p2 = self.element_list[2].mean_load / self.t_curr
                    p3 = self.element_list[3].mean_load / self.t_curr
                    avg_load_end = (p1 + p2 + p3) / 3
                    delta = math.fabs(avg_load_end - avg_load_start)
                    print(f"Current time:{self.t_curr} | Respond:{avg_load_end} | Delta:{delta}")
                    if 0.0 < delta < epsilon:
                        self.stable.append(1)
                        if len(self.stable) >= 5:
                            i = -1
                            while i > -6:
                                if self.stable[i] == 1:
                                    i -= 1
                                    if i == -6:
                                        print("End termination period")
                                        exit()

                                else:
                                    break
                    else:
                        self.stable.append(0)

            if self.element_list[self.event].id_element == 0:
                self.element_list[self.event].print_create_info(flag_info)
            self.print_info(flag_info)

        self.print_result(flag_info)
        self.experiments()

    def print_info(self, flag):
        if flag is True:
            for element in self.element_list:
                element.print_info()

    def print_result(self, flag):
        if flag is True:
            print("\n---- RESULTS ---")
            for element in self.element_list:
                element.print_res()
                if isinstance(element, Create):
                    income_task = element.quantity
                elif isinstance(element, Process):
                    p = element
                    self.general_mean_load += p.mean_load / self.t_curr
                    self.general_mean_queue += p.mean_queue / self.t_curr
                    print(f"Mean length of queue: {p.mean_queue/self.t_curr}  |  Max queue: {p.count_max_queue}"
                          f"\nMean load: {p.mean_load / self.t_next}")

                    if p.directive_fail >= 0:
                        print(f"\n")
                        print(f"Count of tasks that failed directive term: {p.directive_fail}")
                        print(f"Rate of completed tasks:{p.quantity / income_task}")

            print(f"\nAverage load of system processors: {self.general_mean_load/3}")
            print(f"Average queue of system processors: {self.general_mean_queue / 3}")


    def experiments(self):
        avg_mean_load, avg_mean_queue = 0.0, 0.0
        income, done = 0, 0
        for e in self.element_list:
            if isinstance(e, Create):
                income = e.quantity
            elif isinstance(e, Process):
                avg_mean_load += e.mean_load / self.t_curr
                avg_mean_queue += e.mean_queue / self.t_curr
                if e.id_element == 3:
                    done = e.quantity
        Model.general_mean_load = avg_mean_load / 3
        Model.general_mean_queue = avg_mean_queue / 3
        Model.rate_task = done / income
        result = pd.DataFrame([[Model.general_mean_queue, Model.general_mean_load, Model.rate_task]], columns=col_name)
        Model.Result_table = Model.Result_table.append(result, ignore_index=True)
