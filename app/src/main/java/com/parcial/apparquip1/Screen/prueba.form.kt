package com.parcial.apparquip1.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Clase Memento que solo almacena el estado
class Memento(private val state: String) {
    fun getState() = state
}

// Clase Originator que crea y restaura los mementos
class Originator {
    private var state: String = ""

    fun setState(newState: String) {
        state = newState
    }

    fun getState() = state

    fun save(): Memento {
        return Memento(state)
    }

    fun restore(memento: Memento) {
        state = memento.getState()
    }
}

// Clase Caretaker que maneja el historial
class Caretaker {
    private val history = mutableListOf<Memento>()
    private var currentIndex = -1

    fun addMemento(memento: Memento) {
        if (currentIndex < history.size - 1) {
            println("Recuerdo agregado en el  ${history.size}")
            history.subList(currentIndex + 1, history.size).clear()
        }
        history.add(memento)
        currentIndex = history.size - 1
        println("Recuerdo agregado en el  ${history.size}")
    }

    fun undo(): Memento? {
        if (currentIndex > 0) {
            return history[--currentIndex]
        }
        return null
    }

    fun redo(): Memento? {
        if (currentIndex < history.size - 1) {
            return history[++currentIndex]
        }
        return null
    }

    fun canUndo() = currentIndex > 0
    fun canRedo() = currentIndex < history.size - 1
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MementoScreen() {
    val originator = remember { Originator() }
    val caretaker = remember { Caretaker() }
    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Memento Pattern") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                    originator.setState(newText)
                    caretaker.addMemento(originator.save())
                    println("Recuerdo")
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Escribe algo...") }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        caretaker.undo()?.let { memento ->
                            originator.restore(memento)
                            text = originator.getState()
                        }
                    },
                    enabled = caretaker.canUndo()
                ) {
                    Text("Deshacer")
                }

                Button(
                    onClick = {
                        caretaker.redo()?.let { memento ->
                            originator.restore(memento)
                            text = originator.getState()
                        }
                    },
                    enabled = caretaker.canRedo()
                ) {
                    Text("Rehacer")
                }
            }
        }
    }
}