package nodes

class BSNode<K : Comparable<K>, V>(key: K, value: V) : abstractNode<K, V, BSNode<K, V>>(key, value)