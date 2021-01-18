from math_modules import *
import numpy as np


class Element(object):
    next_id = 0

    def __init__(self, name_element=None, delay=None, delay_dev=None, k=None):
        self.next_element = None
        self.previous_element = None
        self.independent_quantity = k
        self.t_next = [0]
        self.t_curr = self.t_next
        self.state = [0]
        self.id_element = Element.next_id
        Element.next_id += 1
        self.name = ('Element:', self.id_element)
        self.quantity = 0
        self.delay_dev = delay_dev
        self.delay_mean = delay
        self.interval = 0
        self.distribution = str()
        self.probability = [1]
        self.priority = []
        self.type_task = 0
        if delay is None and name_element is None:
            self.delay_mean = [1.0]
            self.distribution = 'exp'
        elif delay is not None and name_element is None:
            self.delay_mean = delay
            self.distribution = str('')
        elif delay is not None and name_element is not None:
            self.delay_mean = delay
            self.distribution = 'exp'

    @property
    def delay_mean(self):
        return self.__delay_mean

    @delay_mean.setter
    def delay_mean(self, delay_mean):
        self.__delay_mean = delay_mean

    @property
    def delay_dev(self):
        return self.__delay_dev

    @delay_dev.setter
    def delay_dev(self, delay_dev):
        self.__delay_dev = delay_dev

    @property
    def quantity(self):
        return self.__quantity

    @quantity.setter
    def quantity(self, quantity):
        self.__quantity = quantity

    @property
    def distribution(self):
        return self.__distribution

    @distribution.setter
    def distribution(self, distribution):
        self.__distribution = distribution

    @property
    def state(self):
        return self.__state

    @state.setter
    def state(self, state):
        self.__state = state

    @property
    def next_element(self):
        return self.__next_element

    @next_element.setter
    def next_element(self, next_element):
        self.__next_element = next_element

    @property
    def t_curr(self):
        return self.__t_curr

    @t_curr.setter
    def t_curr(self, t_curr):
        self.__t_curr = t_curr

    @property
    def t_next(self):
        return self.__t_next

    @t_next.setter
    def t_next(self, t_next):
        self.__t_next = t_next

    @property
    def id_element(self):
        return self.__id_element

    @id_element.setter
    def id_element(self, id_element):
        self.__id_element = id_element

    @property
    def name(self):
        return self.__name

    @name.setter
    def name(self, name):
        self.__name = name

    @property
    def probability(self):
        return self.__probability

    @probability.setter
    def probability(self, value):
        self.__probability = value

    def get_delay(self, index=None):
        if index is not None:
            if self.distribution == 'expo':
                delay = exp(self.delay_mean[index])
            elif self.distribution == 'normal':
                delay = norm(self.delay_mean[index], self.delay_dev)
            elif self.distribution == 'uniform':
                delay = unif(self.delay_mean[index], self.delay_dev)
            elif self.distribution == 'erlang':
                delay = erlang(self.delay_mean[index], self.independent_quantity)
            elif self.distribution == 'empirical':
                delay = empirical(self.delay_mean[index], self.delay_dev)
        else:
            if self.distribution == 'expo':
                delay = exp(self.delay_mean)
            elif self.distribution == 'normal':
                delay = norm(self.delay_mean, self.delay_dev)
            elif self.distribution == 'uniform':
                delay = unif(self.delay_mean, self.delay_dev)
            elif self.distribution == 'erlang':
                delay = erlang(self.delay_mean, self.independent_quantity)
            elif self.distribution == 'empirical':
                delay = empirical(self.delay_mean, self.delay_dev)
            else:
                delay = self.delay_mean
        return delay

    def get_unavailable_status(self, type_task):
        max_unavailable_devices, unavailable_devices = 0, 0
        element = self.next_element[type_task-1]
        max_unavailable_devices += element.channel
        for available in range(element.channel):
            unavailable_devices += element.state[available]
        return max_unavailable_devices, unavailable_devices

    def able_to_move(self, type_task):
        if len(self.priority) > 0:
            self.priority[0].in_act(type_task)
        elif len(self.probability) > 1:
            next_el = np.random.choice(a=self.next_element, p=self.probability)
            next_el.in_act(type_task)
        elif len(self.priority) == 0 and len(self.probability) == 1:
            self.get_next_status_block(type_task)

    def get_next_status_block(self, type_task):
        next_element = self.next_element[type_task-1]
        if next_element is not None:
            for device in range(next_element.channel):
                if next_element.state[device] == 0:
                    next_element.in_act(type_task)
                    break

    def in_act(self, type_task):
        pass

    def out_act(self):
        self.quantity += 1

    def statistics(self, delta):
        pass

    def print_res(self):
        print(f"\n{self.name} quantity={self.quantity}")

    def print_info(self):
        print(f"{self.name} state={self.state} quantity={self.quantity} tnext={self.t_next}")

