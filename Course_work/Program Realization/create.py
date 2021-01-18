from element import *


class Create(Element):
    def __init__(self, interval1, smo1_delay, smo2_delay, smo3_delay,  interval2):
        super().__init__(name_element=None, delay=interval1[0])
        self.distribution = 'unif'
        self.delay_dev = interval1[1]
        self.delay1, self.delay2, self.delay3 = smo1_delay, smo2_delay, smo3_delay
        self.smo1_delay, self.smo2_delay, self.smo3_delay = 0.0, 0.0, 0.0
        self.add_time_min = interval2[0]
        self.add_time_max = interval2[1]
        self.general_time, self.techno_time, self.add_time = 0.0, 0.0, 0.0
        self.properties = []

    def out_act(self):
        super().out_act()
        self.smo1_delay = exp(self.delay1)
        self.smo2_delay = exp(self.delay2)
        self.smo3_delay = exp(self.delay3)
        self.add_time = unif(self.add_time_min, self.add_time_max)
        self.general_time = self.smo1_delay + self.smo2_delay + self.smo3_delay
        self.techno_time = self.general_time + self.add_time
        delay = unif(self.delay_mean, self.delay_dev)
        self.t_next[0] = self.t_curr + delay
        directive_term = self.t_curr + self.techno_time + delay
        self.properties = [self.smo1_delay, self.smo2_delay, self.smo3_delay, self.add_time, self.general_time, directive_term]
        self.next_element[0].in_act(self.properties)

    def print_create_info(self, flag):
        if flag is True:
            print(f"Programming delay:{self.properties[0]} | Hardware write delay:{self.properties[1]} | Testing delay:{self.properties[2]}")
            print(f"Technical delay:{self.techno_time} | Directive term {self.properties[5]}")
