from Model import *
from Position import *
from Transition import *

'''
p1 = Position('REQUEST FROM A TO B')
p2 = Position('READY TO SEND A TO B')
p3 = Position('SENDING FROM A')
p4 = Position('GETTING IN B')
p5 = Position('READY B')
p6 = Position('REQUEST FROM B TO A')
p7 = Position('READY TO SEND B TO A')
p8 = Position('SENDING FROM B')
p9 = Position('GETTING IN A')
p10 = Position('READY A')
p11 = Position('MAIN SIGNAL')

t1 = Transition('SENDING IS ABLE FROM A TO B')
t2 = Transition('SENDING A FROM B')
t3 = Transition('GETTING IN B FROM A')
t4 = Transition('SUCCESS SENDING A TO B')
t5 = Transition('SENDING IS ABLE FROM B TO A')
t6 = Transition('SENDING B FROM A')
t7 = Transition('GETTING IN A FROM B')
t8 = Transition('SUCCESS SENDING B TO A')

p1.marker = 1
p5.marker = 1
p6.marker = 1
p10.marker = 1
p11.marker = 1

p1.transition_move = [t1]
p2.transition_move = [t2]
p3.transition_move = [t3]
p4.transition_move = [t4]
p5.transition_move = [t1]
p6.transition_move = [t5]
p7.transition_move = [t6]
p8.transition_move = [t7]
p9.transition_move = [t8]
p10.transition_move = [t5]
p11.transition_move = [t2, t6]

t1.previous_positions = [p1, p5]
t2.previous_positions = [p2, p11]
t3.previous_positions = [p3]
t4.previous_positions = [p4]
t5.previous_positions = [p6, p10]
t6.previous_positions = [p7, p11]
t7.previous_positions = [p8]
t8.previous_positions = [p9]

t1.move_to_positions = [p2]
t2.move_to_positions = [p3]
t3.move_to_positions = [p4]
t4.move_to_positions = [p5, p11, p1]
t5.move_to_positions = [p7]
t6.move_to_positions = [p8]
t7.move_to_positions = [p9]
t8.move_to_positions = [p10, p11, p6]

pos_list = [p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11]
t_list = [t1, t2, t3, t4, t5, t6, t7, t8]

model = Model(pos_list, t_list)
model.simulate(1000)


p1 = Position('PRODUCER\'S NEW TASK')
p2 = Position('PRODUCER\'S TASKS QUEUE')
p3 = Position('PUT PROCESS IN ACTION')
p4 = Position('CONSUMER\'S NEW TASK')
p5 = Position('CONSUMER\'S TASKS QUEUE')
p6 = Position('TAKE PROCESS IN ACTION')
p7 = Position('CONSUMER\'S DONE COUNT')
p8 = Position('BUFFER')
p9 = Position('BUFFER LIMITATION')

t1 = Transition('NEW TASK FOR PRODUCER INCOME')
t2 = Transition('PUT')
t3 = Transition('END PUT')
t4 = Transition('NEW TASK FOR CONSUMER INCOME')
t5 = Transition('TAKE')
t6 = Transition('END TAKE')

p1.marker = 1
p4.marker = 1
p9.marker = 2


p1.transition_move = [t1]
p2.transition_move = [t2]
p3.transition_move = [t3]
p4.transition_move = [t4]
p5.transition_move = [t5]
p6.transition_move = [t6]
p8.transition_move = [t5]
p9.transition_move = [t2]

t1.previous_positions = [p1]
t2.previous_positions = [p2, p9]
t3.previous_positions = [p3]
t4.previous_positions = [p4]
t5.previous_positions = [p5, p8]
t6.previous_positions = [p6]

t1.move_to_positions = [p2, p1]
t2.move_to_positions = [p3]
t3.move_to_positions = [p8]
t4.move_to_positions = [p5, p4]
t5.move_to_positions = [p6]
t6.move_to_positions = [p7, p9]

pos_list = [p1, p2, p3, p4, p5, p6, p7, p8, p9]
t_list = [t1, t2, t3, t4, t5, t6]

model = Model(pos_list, t_list)
model.simulate(1000)
'''
p1 = Position('NEW TASKS')
p2 = Position('RANDOM TASK')
p3 = Position('QUEUE TASK #1')
p4 = Position('QUEUE TASK #2')
p5 = Position('QUEUE TASK #3')
p6 = Position('PROCESSING TASK #1')
p7 = Position('PROCESSING TASK #2')
p8 = Position('PROCESSING TASK #3')
p9 = Position('PROCESSED TASK #1')
p10 = Position('PROCESSED TASK #2')
p11 = Position('PROCESSED TASK #3')
p12 = Position('RESOURCES')

t1 = Transition('NEW TASK INCOME')
t2 = Transition('TASK #1 IN QUEUE OF TASKS')
t3 = Transition('TASK #2 IN QUEUE OF TASKS')
t4 = Transition('TASK #3 IN QUEUE OF TASKS')
t5 = Transition('TASK #1 START PROCESSING')
t6 = Transition('TASK #2 START PROCESSING')
t7 = Transition('TASK #3 START PROCESSING')
t8 = Transition('TASK #1 END PROCESSING')
t9 = Transition('TASK #2 END PROCESSING')
t10 = Transition('TASK #3 END PROCESSING')

p1.marker = 1
p12.marker = 6

p1.transition_move = [t1]
p2.transition_move = [t2, t3, t4]
p3.transition_move = [t5]
p4.transition_move = [t6]
p5.transition_move = [t7]
p6.transition_move = [t8]
p7.transition_move = [t9]
p8.transition_move = [t10]
p12.transition_move = [t5, t6, t7]
p12.multiplicity = [6, 2, 3]

t1.previous_positions = [p1]
t2.previous_positions = [p2]
t3.previous_positions = [p2]
t4.previous_positions = [p2]
t5.previous_positions = [p3, p12]
t6.previous_positions = [p4, p12]
t7.previous_positions = [p5, p12]
t8.previous_positions = [p6]
t9.previous_positions = [p7]
t10.previous_positions = [p8]

t1.move_to_positions = [p1, p2]
t2.move_to_positions = [p3]
t3.move_to_positions = [p4]
t4.move_to_positions = [p5]
t5.move_to_positions = [p6]
t6.move_to_positions = [p7]
t7.move_to_positions = [p8]
t8.move_to_positions = [p9, p12]
t9.move_to_positions = [p10, p12]
t10.move_to_positions = [p11, p12]

t8.multiplicity = [1, 6]
t9.multiplicity = [1, 2]
t10.multiplicity = [1, 3]


pos_list = [p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12]
t_list = [t1, t2, t3, t4, t5, t6, t7, t8, t9, t10]

model = Model(pos_list, t_list)
model.simulate(100)
