package nodes

class AVLNode<K : Comparable<K>, V>(key: K, value: V): abstractNode<K, V, AVLNode<K, V>>(key, value) {
    var height = 1
}