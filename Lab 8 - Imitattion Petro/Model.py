from Transition import *


class Model(object):

    def __init__(self, position_list, transition_list, tact=1):
        self.tact = tact
        self.position_list = position_list
        self.transition_list = transition_list
        self.t_curr = 0

    def simulate(self, time):
        while self.t_curr < time:
            print(f'\n-----------------------'
                  f'\n\tTACT TIME:{self.t_curr}'
                  f'\n-----------------------')
            for position in self.position_list:
                position.arc_condition()

            for position in self.position_list:
                position.check_conflict()

            for element in self.transition_list:
                element.transition_start()
            self.t_curr += self.tact
            print(f'\n-----------------------'
                  f'\n\tTACT TIME:{self.t_curr}'
                  f'\n-----------------------')
            for element in self.transition_list:
                element.transition_end()
            for element in self.position_list:
                element.statistics(self.t_curr, (self.t_curr-self.tact))

        print('\n=========================================')
        for element in self.position_list:
            print(f'\nObserved P: {element.name} '
                  f'\nMax number: {element.max_marker} | '
                  f'Min number: {element.min_marker} | '
                  f'Average number: {element.average_markers/self.t_curr}')

        print('\n=========================================\n')
        for element in self.transition_list:
            print(f'T: {element.name} was involved: {element.int_action} times.')
