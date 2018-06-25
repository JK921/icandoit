# 순열
> Next Permutations

1. **arr[i - 1] < arr[i]** 를 만족하는 가장 큰 i를 찾는다
2. j >= i이면서 **arr[i-1] < arr[j]** 를 만족하는 가장 큰 j 를 찾는다
3. arr[i - 1] 과 arr[j]를 **swap** 한다
4. arr[i] 부터 순열을 뒤집는다. **reverse**


## 문제풀이

### 이전 순열
* src/Solution10973.java
* [https://www.acmicpc.net/problem/10973](https://www.acmicpc.net/problem/10973)

> 모든 순열을 구해서 정답을 찾는것이 아님. 범위가 너무 큼 **N(1 ≤ N ≤ 10,000)** <br>
> 입력 값을 기준으로 다음 순열을 구하면 됨. <br>

### 모든 순열
* src/Solution10974.java
* [https://www.acmicpc.net/problem/10974](https://www.acmicpc.net/problem/10974)

> 범위가 **N(1 ≤ N ≤ 8)** 즉, 8! 이기에 가능. <br>
> 10! 은 **3628800** <br>

### 순열의 순서
* src/Solution1722.java
* [https://www.acmicpc.net/problem/1722](https://www.acmicpc.net/problem/1722)

> 반대방향 순열을 구할때는 부등호만 반대. reverse 는 해 줘야 함. <br>


