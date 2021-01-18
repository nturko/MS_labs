from Model import *
from Position import *
from Transition import *

'''
p1 = Position()
p2 = Position()
p3 = Position()
p4 = Position()
p5 = Position()
p5.multiplicity = 0

t1 = Transition()
t2 = Transition()
t3 = Transition()
t4 = Transition()

p1.transition_move = [t1, t3]
p2.transition_move = [t2]
p3.transition_move = [t1]
p4.transition_move = [t4]

p1.marker = 1
p1.probability = [0.5, 0.5]

p3.marker = 1

t1.previous_positions = [p1, p3]
t1.move_to_positions = [p1, p2]

t2.previous_positions = [p2]
t2.move_to_positions = [p3, p5]

t3.previous_positions = [p1]
t3.move_to_positions = [p1, p4]

t4.previous_positions = [p4]
t4.move_to_positions = [p5]

position_list = [p1, p2, p3, p4, p5]
transition_list = [t1, t2, t3, t4]

model = Model(position_list, transition_list)
model.simulate(10)


p1 = Position()
p2 = Position()
p3 = Position()
p4 = Position()
p5 = Position()

t1 = Transition()
t2 = Transition()
t3 = Transition()

p1.marker = 1
p1.transition_move = [t1]

p2.transition_move = [t2, t3]
p2.probability = [0.5, 0.5]

p5.marker = 10
p5.multiplicity = [2]
p5.transition_move = [t2]

t1.previous_positions = [p1]
t1.move_to_positions = [p1, p2]

t2.previous_positions = [p2, p5]
t2.move_to_positions = [p4]

t3.previous_positions = [p2]
t3.move_to_positions = [p3]

position_list = [p1, p2, p3, p4, p5]
transition_list = [t1, t2, t3]

model = Model(position_list, transition_list)
model.simulate(1000)


p1 = Position()
p2 = Position()
p3 = Position()

p4 = Position()
p5 = Position()
p6 = Position()

p7 = Position()

t1 = Transition()
t2 = Transition()
t3 = Transition()
t4 = Transition()


p1.transition_move = [t1]
p2.transition_move = [t2]
p3.transition_move = [t3]

p4.transition_move = [t4]
p5.transition_move = [t4]
p6.transition_move = [t4]

t1.previous_positions = [p1]
t1.previous_positions = [p2]
t1.previous_positions = [p3]
t1.previous_positions = [p4, p5, p6]


t1.move_to_positions = [p1, p4]
t1.move_to_positions = [p2, p5]
t1.move_to_positions = [p3, p5]
t1.move_to_positions = [p7]

t1.multiplicity = [10]
t2.multiplicity = []


'''
p1 = Position()
p2 = Position()
p3 = Position()
p4 = Position()
p5 = Position()
p6 = Position()

t1 = Transition()
t2 = Transition()
t3 = Transition()
t4 = Transition()
t5 = Transition()

p1.marker = 1
p3.marker = 1

p2.probability = [0.5, 0.5]

p1.transition_move = [t1]
p2.transition_move = [t2, t3]
p3.transition_move = [t2]
p4.transition_move = [t4]
p5.transition_move = [t5]

t1.previous_positions = [p1]
t2.previous_positions = [p2, p3]
t3.previous_positions = [p2]
t4.previous_positions = [p4]
t5.previous_positions = [p5]

t1.move_to_positions = [p1, p2]
t2.move_to_positions = [p4]
t3.move_to_positions = [p5]
t4.move_to_positions = [p3, p6]
t5.move_to_positions = [p6]

pos_list = [p1, p2, p3, p4, p5, p6]
t_list = [t1, t2, t3, t4, t5]

model = Model(pos_list, t_list)
model.simulate(100)
