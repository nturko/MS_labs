from element import *
import numpy as np


class ProcessHospital(Element):

    def __init__(self, delay, channels=1, delay_dev=0, k=0, change_type=None):
        super().__init__(name_element=None, delay=delay, delay_dev=delay_dev, k=k)
        self.queue = [0]*len(delay)
        self.max_queue = np.inf
        self.mean_queue, self.mean_load = 0.0, 0.0
        self.system_tasks = 0
        self.failure = 0
        self.channel = channels
        self.t_next = [np.inf]*self.channel
        self.state = [0]*self.channel
        self.state_type = [0]*self.channel
        self.interval = 0
        self.change_type = change_type

    def in_act(self, type_of_task):
        self.type_task = type_of_task
        free_route = self.get_free_channels()
        if len(free_route) > 0:
            for i in free_route:
                self.state[i] = 1
                self.state_type[i] = self.type_task
                if self.type_task == 1:
                    self.t_next[i] = self.t_curr + super().get_delay(self.type_task-1)
                elif self.type_task == 2:
                    self.t_next[i] = self.t_curr + super().get_delay(self.type_task-1)
                if self.type_task == 3:
                    self.t_next[i] = self.t_curr + super().get_delay(self.type_task-1)
                break
        else:
            if sum(self.queue) < self.max_queue:
                self.queue[self.type_task-1] += 1
            else:
                self.failure += 1

    def out_act(self):
        current_channel = self.get_current_channel()
        for index in current_channel:
            self.type_task = self.state_type[index]
            if self.next_element is not None:
                next_element = self.next_element[self.type_task-1]
                max_unavailable_devices, unavailable_devices = self.get_unavailable_status(self.type_task)
                if unavailable_devices < max_unavailable_devices:
                    self.get_introvert_type()
                    self.able_to_move(self.type_task)
                    super().out_act()
                    self.unblock_device(index)
                elif unavailable_devices == max_unavailable_devices:
                    if sum(next_element.queue) == next_element.max_queue:
                        self.t_next[index] = np.inf
                    elif sum(next_element.queue) < next_element.max_queue:
                        super().out_act()
                        next_element.in_act(self.type_task)
                        self.unblock_device(index)
            else:
                super().out_act()
                self.unblock_device(index)
            '''
            if self.next_element is not None:
                next_el = np.random.choice(a=self.next_element, p=self.probability)
                next_el.in_act()
            '''

    def unblock_device(self, index):
        self.t_next[index] = np.inf
        self.state[index] = 0
        self.state_type[index] = 0
        if sum(self.queue) > 0:
            if self.queue[0] > 0:
                self.queue[0] = self.queue[0] - 1
                self.type_task = 1
                self.state[index] = 1
                self.state_type[index] = 1
                self.t_next[index] = self.t_curr + super().get_delay(self.type_task-1)
                self.get_previous_status_block()
            elif self.queue[2] > 0:
                self.queue[2] = self.queue[2] - 1
                self.type_task = 3
                self.state[index] = 1
                self.state_type[index] = 3
                self.t_next[index] = self.t_curr + super().get_delay(self.type_task-1)
                self.get_previous_status_block()
            elif self.queue[1] > 0:
                self.queue[1] = self.queue[1] - 1
                self.type_task = 2
                self.state[index] = 1
                self.state_type[index] = 2
                self.t_next[index] = self.t_curr + super().get_delay(self.type_task-1)
                self.get_previous_status_block()

    def get_previous_status_block(self):
        if self.previous_element is not None:
            for element in self.previous_element:
                for device in range(element.channel):
                    if element.state[device] == 1 and element.t_next[device] == np.inf:
                        element.out_act()
                        break
                else:
                    continue
                break

    def get_introvert_type(self):
        if self.change_type == 'able' and self.type_task == 2:
            self.type_task = 1
        else:
            pass

    def get_free_channels(self):
        free_channels = []
        for i in range(self.channel):
            if self.state[i] == 0:
                free_channels.append(i)
        return free_channels

    def get_current_channel(self):
        current_channels = []
        for i in range(self.channel):
            if self.t_next[i] == self.t_curr:
                current_channels.append(i)
        return current_channels

    @property
    def channel(self):
        return self.__channel

    @channel.setter
    def channel(self, value):
        self.__channel = value

    @property
    def max_queue(self):
        return self._max_queue

    @max_queue.setter
    def max_queue(self, value):
        if value >= 0:
            self._max_queue = value
        else:
            print("Whoops, max queue doesn't support this type")

    def print_res(self):
        print(f"\n{self.name} quantity={self.quantity }")

    def print_info(self):
        super(ProcessHospital, self).print_info()
        print(f'Queue: {self.queue} Type of patients in states:{self.state_type}')

    def statistics(self, delta):
        self.mean_queue += sum(self.queue) * delta
        self.interval += delta
        self.mean_load += sum(self.state) * delta


