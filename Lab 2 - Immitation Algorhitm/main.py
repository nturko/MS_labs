from math_modules import *


class Model(object):
    def __init__(self, delay_create, delay_process, max_queue=0):
        self.delay_create = float(delay_create)
        self.delay_process = float(delay_process)
        self.max_queue = max_queue
        self.t_next = 0.0
        self.t_curr = self.t_next
        self.t_0 = self.t_curr
        self.t_1 = float('inf')
        self.state, self.queue, self.next_event = 0, 0, 0
        self.num_create, self.num_process, self.failure = 0, 0, 0
        self.load_list = list()
        self.queue_list = list()

    def simulate(self, time_modeling, flag):
        while self.t_curr < time_modeling:
            self.t_next = self.t_0
            self.next_event = 0

            if self.t_1 < self.t_next:
                self.t_next = self.t_1
                self.next_event = 1

            self.queue_list.append((self.t_next - self.t_curr) * self.queue)
            self.load_list.append((self.t_next - self.t_curr) * self.state)

            self.t_curr = self.t_next
            if self.next_event == 0:
                self.create_event()
            else:
                self.process_event()

            if flag:
                self.print_info()
        self.print_statistic(flag)

    def create_event(self):
        self.t_0 = self.t_curr + self.get_delay_of_create()
        self.num_create += 1
        if self.state == 0:
            self.state = 1
            self.t_1 = self.t_curr + self.get_delay_of_process()
        else:
            if self.queue < self.max_queue:
                self.queue += 1
            else:
                self.failure += 1

    def process_event(self):
        self.t_1 = float('inf')
        self.state = 0
        if self.queue > 0:
            self.queue -= 1
            self.state = 1
            self.t_1 = self.t_curr + self.get_delay_of_process()
        self.num_process += 1

    def get_delay_of_create(self):
        return exp(self.delay_create)

    def get_delay_of_process(self):
        return exp(self.delay_process)

    def print_info(self):
        print(f"t:{int(self.t_curr)} --- State:{self.state} ---- Queue:{self.queue}")

    def print_statistic(self, flag):
        if flag:
            print("\n\n --- Verification ---")
        print(f"\nDelay of create:{self.delay_create} | Delay of process:{self.delay_process} | MaxQueue:{self.max_queue}"
              f"\nCount of created: {self.num_create} | Count of processed: {self.num_process + self.state} "
              f"| Failures: {self.failure} \nTheoretical failure probability: {self.failure/self.num_create} "
              f"\nAverage queue: {sum(self.queue_list)/self.t_next} \nAverage load: {sum(self.load_list)/self.t_next}"
              f"\nAverage waiting time: {sum(self.queue_list)/self.num_process}")


if __name__ == '__main__':
    mod = Model(2, 1, 5)
    mod.simulate(1000, True)



