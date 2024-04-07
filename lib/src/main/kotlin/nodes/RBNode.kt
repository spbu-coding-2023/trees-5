package nodes

enum class Color {
    RED, BLACK
}

class RBNode<K : Comparable<K>, V>(key: K, value: V): AbstractNode<K, V, RBNode<K, V>>(key, value) {
    var color: Color = Color.RED
}