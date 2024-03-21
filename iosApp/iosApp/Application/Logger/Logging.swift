//
//  Logging.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 14/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

public protocol LoggerDelegate {
    func loggerUpdated()
}

public enum LogLevel: Int {
    case debug
    case info
    case warning
    case error
    case custom
    case none
}

open class Logger {
    /// eventBuffer limit per action
    static let maxEventsBufferSize = 50

    /// hold all events in memory
    open var logBuffer: [AnyObject]

    /// logLevel
    open var logLevel: LogLevel

    /// logDetails
    open var logDetails: Bool = true

    /// Logger Delegate
    open var delegate: LoggerDelegate?

    // MARK: - sharedInstance for singleton access

    static let sharedInstance = Logger()

    // MARK: - Init Methods

    public init() {
        self.logBuffer = []
        self.logLevel = .debug
    }

    // MARK: - Clear logs

    open func clearLogs() {
        logBuffer.removeAll()

        if let delegate = delegate {
            delegate.loggerUpdated()
        }
    }

    // MARK: - Log Methods

    func LogDebug(_ msg: String, function: String = #function, file: String = #file, line: Int = #line) {
        #if RELEASE
        // do not log
        #else
        if logLevel.rawValue <= LogLevel.debug.rawValue {
            print("[DEBUG] \(makeTag(function, file: file, line: line, msg: msg))")
        }
        #endif
    }

    func LogInfo(_ msg: String, function: String = #function, file: String = #file, line: Int = #line) {
        #if RELEASE
        // do not log
        #else
        if logLevel.rawValue <= LogLevel.info.rawValue {
            print("[INFO] \(makeTag(function, file: file, line: line, msg: msg))")
        }
        #endif
    }

    func LogWarning(_ msg: String, function: String = #function, file: String = #file, line: Int = #line) {
        #if RELEASE
        AnalyticsController.sharedInstance.logEvent(
            AnalyticsEvents.LOG_WARNING,
            attributes: ["info": makeTag(function, file: file, line: line, msg: msg)]
        )
        #else
        if logLevel.rawValue <= LogLevel.warning.rawValue {
            print("[WARNING] \(makeTag(function, file: file, line: line, msg: msg))")
        }
        #endif
    }

    func LogError(_ msg: String, function: String = #function, file: String = #file, line: Int = #line) {
        #if RELEASE
        AnalyticsController.sharedInstance.logEvent(
            AnalyticsEvents.LOG_ERROR,
            attributes: ["info": makeTag(function, file: file, line: line, msg: msg)]
        )
        #else
        if logLevel.rawValue <= LogLevel.error.rawValue {
            print("[ERROR] \(makeTag(function, file: file, line: line, msg: msg))")
        }
        #endif
    }

    func LogCustom(_ msg: String, function: String = #function, file: String = #file, line: Int = #line) {
        #if RELEASE
        AnalyticsController.sharedInstance.logEvent(
            AnalyticsEvents.LOG_WARNING,
            attributes: ["info": makeTag(function, file: file, line: line, msg: msg)]
        )
        #else
        if logLevel.rawValue == LogLevel.custom.rawValue {
            print("[CUSTOM] \(makeTag(function, file: file, line: line, msg: msg))")
        }
        #endif
    }

    private func makeTag(_ function: String, file: String, line: Int, msg: String) -> String {
        let formatter = DateFormatter()
        formatter.timeStyle = .medium
        let timeString = formatter.string(from: Date())

        var logString = ""

        if logDetails {
            let url = URL(fileURLWithPath: file)
            let className = url.lastPathComponent
            logString = "\(timeString): \(className) \(function)[\(line) : \(msg)]"
        } else {
            logString = "\(msg)"
        }

        // append to buffer size
        logBuffer.append(logString as AnyObject)

        // remove oldest log if bufferSize reached
        if logBuffer.count >= Logger.maxEventsBufferSize {
            logBuffer.remove(at: 0)
        }

        if let delegate = delegate {
            delegate.loggerUpdated()
        }

        return logString
    }
}
