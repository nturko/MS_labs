from process_bank import *


class ModelBank(object):

    def __init__(self, elements_list):
        self.element_list = elements_list
        self.t_next = 0
        self.event = 0
        self.t_curr = 0
        self.queue_move = 0
        self.time = 0
        self.all_clients, self.out_clients = 0, 0

    def simulate(self, time, flag):
        while self.t_curr < time:
            self.t_next = np.inf
            for element in self.element_list:
                t_next_val = np.min(element.t_next)
                if t_next_val < self.t_next:
                    self.t_next = t_next_val
                    self.event = element.id_element
            if flag is True:
                print(f"\nIt's time for {self.element_list[self.event].name}, time= {self.t_next}")
            for element in self.element_list:
                element.statistics(self.t_next - self.t_curr)

            self.t_curr = self.t_next
            for element in self.element_list:
                element.t_curr = self.t_curr

            self.element_list[self.event].out_act()
            for element in self.element_list:
                if self.t_curr in element.t_next:
                    element.out_act()
            self.change_queue()
            self.print_info(flag)
        self.print_result(flag)

    def change_queue(self):
        queue_list = list()
        for element in self.element_list:
            if isinstance(element, ProcessBank):
                queue_list.append(element.queue)
        q_1 = queue_list[0] - queue_list[1]
        q_2 = queue_list[1] - queue_list[0]
        if q_1 >= 2:
            self.element_list[1].queue -= 1
            self.element_list[2].queue += 1
            print("From CASHIER_1's queue one car left to CASHIER_2 queue.")
            self.queue_move += 1
        elif q_2 >= 2:
            self.element_list[2].queue -= 1
            self.element_list[1].queue += 1
            print("From CASHIER_2's queue one car left to CASHIER_1 queue.")
            self.queue_move += 1

    def print_info(self, flag):
        if flag is True:
            for element in self.element_list:
                element.print_info()

    def print_result(self, flag):
        if flag is True:
            print("\n---- RESULTS ---")
            for element in self.element_list:
                element.print_res()
                if isinstance(element, ProcessBank):
                    p = element
                    self.all_clients += p.system_tasks
                    self.out_clients += p.quantity
                    print(f"Mean length of queue={p.mean_queue/self.t_curr}  |  Max queue={p.max_queue}"
                          f" \nFailure probability={p.failure/(p.quantity + p.failure)}  |  Mean load={p.mean_load / self.t_curr}"
                          f"\nInterval of time car moving from CASHIER_{p.id_element}: {p.quantity / self.t_curr} ")
            print(f"\nCount of cars that changed cashier:{self.queue_move}"
                  f"\nAverage count of client in the bank:{self.all_clients/self.t_curr}"
                  f"\nAverage time client be in bank:{self.t_curr/self.out_clients}")

