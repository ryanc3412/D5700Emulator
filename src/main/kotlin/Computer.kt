package org.example

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class Computer {
    private val cpu = CPU()
    private val memory = arrayOf(RAM(), ROM())
    private val display = Display()
    private val instructions = FactoryForInstructions(cpu, memory, display)

    private val executor = Executors.newSingleThreadScheduledExecutor()
    private var cpuFuture: ScheduledFuture<*>? = null

    fun start() {
        memory[1].loadROM()
        startEmulation()
    }

    private fun startEmulation() {
        val runnable = Runnable {
            val firstByte = memory[1].read(cpu.counter.toInt())
            val secondByte = memory[1].read(cpu.counter.toInt() + 1)

            if (firstByte == 0.toUByte() && secondByte == 0.toUByte()) {
                stop()
            } else {
                instructions.execute(firstByte, secondByte)
            }
        }

        cpuFuture = executor.scheduleAtFixedRate(
            runnable,
            0,
            500L, // repeat frequency - every 500 ms
            TimeUnit.MILLISECONDS
        )
    }

    private fun stop() {
        cpuFuture?.cancel(true)

        // Shut down the executor and wait for ongoing tasks to complete
        executor.shutdown()
        try {
            if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                executor.shutdownNow()
                if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                    System.err.println("Executor did not terminate in time")
                }
            }
        } catch (interruptedException: InterruptedException) {
            executor.shutdownNow()
            Thread.currentThread().interrupt()
        }
    }
}