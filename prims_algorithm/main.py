from sys import maxsize
import heapq

class Vertex:
    
    def __init__(self, id):
        self.id = id
        self.key = -1
        self.parent = None
        self.neighborhood = []
        
    def __lt__(self, other):
        return self.key < other.key
    
    def __gt__(self, other):
        return self.key > other.key
    
    def __eq__(self, other):
        return self.key == other.key
    
    def __str__(self):
        return f'({self.id}, {self.key})'
    
    def __repr__(self):
        return f'({self.id}, {self.key})'

def mst_prim(adjacency_list, weights, root):
    for vertex in adjacency_list:
        vertex.key = maxsize
        vertex.parent = None
        
    root.key = 0
    
    pq = adjacency_list.copy()
    heapq.heapify(pq)
    
    while len(pq) != 0:
        u = heapq.heappop(pq)
        print([i.key for i in pq])
        for v in u.neighborhood:
            print(f'{u.id}, {v.key}, {weights[u.id][v.id]}')
            if v in pq and weights[u.id][v.id] < v.key:
                # print(f'{v.id}, {v.key}, {weights[u.id][v.id]}')
                print('INNER')
                v.parent = u
                v.key = weights[u.id][v.id]
                
def int_input():
    str_int_arr = input().split(' ')
    return [int(x) for x in str_int_arr]


def main():
    
    # m, n = int_input()

    # adjacency_list = [Vertex(i) for i in range(m)]
    # weights = [[-1 for i in range(m)] for j in range(m)]

    # for i in range(n):
    #     x, y, z = int_input()
        
    #     adjacency_list[x].neighborhood += [adjacency_list[y]]
    #     adjacency_list[y].neighborhood += [adjacency_list[x]]
        
    #     weights[x][y] = z
    #     weights[y][x] = x
    
    m = 6
    adjacency_list = [Vertex(i) for i in range(m)]
    weights = [[-1 for i in range(m)] for j in range(m)]

    test_data = [[0,1,10],[0,2,15],[1,3,12],[2,3,10],[3,4,8],[2,5,20],[4,5,6],[1,4,14],[0,4,18]]
    
    for data in test_data:
        x = data[0]
        y = data[1]
        z = data[2]
        
        adjacency_list[x].neighborhood += [adjacency_list[y]]
        adjacency_list[y].neighborhood += [adjacency_list[x]]
        
        weights[x][y] = z
        weights[y][x] = z
                                
    # print(adjacency_list)                            
    
    mst_prim(adjacency_list, weights, adjacency_list[1])
    
    sum = 0
    
    for v in adjacency_list:
        sum += v.key
        
    print(sum)
    
main()
