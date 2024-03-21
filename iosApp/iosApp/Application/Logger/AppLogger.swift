//
//  AppLogger.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 14/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import class IosData.KotlinThrowable
import protocol IosData.Logger
import Foundation
import os.log


public class AppLogger {
    private static var sharedLoggers: [LogCategory: AppLogger] = [:]
    private let category: LogCategory
    private let log: OSLog
    var logLevel: LogType

    // swiftformat:disable redundantFileprivate
    fileprivate init(category: LogCategory) {
        self.category = category
        let subsystem = Bundle.main.bundleIdentifier!
        switch category {
        case .presentation:
            self.log = OSLog(subsystem: subsystem, category: "PRESENTATION")
        case .ui:
            self.log = OSLog(subsystem: subsystem, category: "UI")
        case .domainData:
            self.log = OSLog(subsystem: subsystem, category: "DOMAIN_DATA")
        }
        #if DEBUG
        self.logLevel = .debug
        #else
        self.logLevel = .error
        #endif
    }

    public class func logger(for category: LogCategory) -> AppLogger {
        if let logger = sharedLoggers[category] {
            return logger
        }

        let logger = AppLogger(category: category)
        sharedLoggers[category] = logger

        return logger
    }

    public class func log(_ category: LogCategory,
                          _ type: LogType,
                          _ message: @autoclosure () -> Any,
                          _ privateMessage: String? = nil)
    {
        AppLogger.logger(for: category)
            .logMessage(type: type, message: message, privateMessage: privateMessage)
    }

    /// This function will log messages to the console and record Crashlytics exception models with the given data.
    /// - Parameters:
    ///   - category: describes in which layer issue occurs.
    ///   - type: describes log level.
    ///   - message: describes which message will be logged, it takes precedence over the message from `kotlinThrowable`.
    ///   - privateMessage: describes private message which will be hidden with <private>, sensitive data i.e. username, password.
    ///   - kotlinThrowable: describes kotlin throwable class within domain exception.
    ///   - causingClass: describes class where log occured i.e. use case, `self`, etc.
    public class func logException(_ category: LogCategory,
                                   _ type: LogType,
                                   _ message: String? = nil,
                                   _ privateMessage: String? = nil,
                                   kotlinThrowable: KotlinThrowable? = nil,
                                   causingClass: Any? = nil)
    {
        guard let message = message ?? kotlinThrowable?.message else { return }
        log(category, type, message, privateMessage)
    }

    public class func logCastException<T>(
        result: Any,
        type: T.Type,
        causingClass: Any?
    ) {
        logException(
            .domainData,
            .debug,
            "Failed to cast \(Swift.type(of: result)) to related type of \(type)",
            causingClass: causingClass
        )
    }

    private func logMessage(type: LogType, message: () -> Any, privateMessage: String?) {
        guard type.level >= logLevel.level else { return }
        let nativeType = nativeLogType(type: type)
        let finalMessage = "\(type.tag) - \(message())"
        if let privateMessage = privateMessage {
            os_log("%{public}@. %{private}@", log: log, type: nativeType, finalMessage, privateMessage)
        } else {
            os_log("%{public}@", log: log, type: nativeType, finalMessage)
        }
    }

    private func nativeLogType(type: LogType) -> OSLogType {
        switch type {
        case .debug:
            return .debug
        case .error:
            return .error
        case .info:
            return .info
        case .fault:
            return .fault
        }
    }
}

public class DataCommonLogger: AppLogger, IosData.Logger {
    public init() {
        super.init(category: .domainData)
    }

    public func d(message: String) {
        Self.log(.domainData, .debug, message)
    }

    public func d(throwable: KotlinThrowable) {
        Self.log(.domainData, .debug, throwable.stackTrace)
    }

    public func d(message: String, throwable: KotlinThrowable) {
        Self.log(.domainData, .debug, throwable.stackTrace)
    }

    public func e(message: String) {
        Self.log(.domainData, .error, message)
    }

    public func e(throwable: KotlinThrowable) {
        Self.log(.domainData, .error, throwable.stackTrace)
    }

    public func e(message: String, throwable: KotlinThrowable) {
        Self.log(.domainData, .error, throwable.stackTrace)
    }

    public func i(message: String) {
        Self.log(.domainData, .info, message)
    }

    public func i(throwable: KotlinThrowable) {
        Self.log(.domainData, .info, throwable.stackTrace)
    }

    public func i(message: String, throwable: KotlinThrowable) {
        Self.log(.domainData, .info, throwable.stackTrace)
    }

    public func v(message: String) {
        d(message: message)
    }

    public func v(throwable: KotlinThrowable) {
        d(throwable: throwable)
    }

    public func v(message: String, throwable: KotlinThrowable) {
        d(message: message, throwable: throwable)
    }

    public func w(message: String) {
        i(message: message)
    }

    public func w(throwable: KotlinThrowable) {
        i(throwable: throwable)
    }

    public func w(message: String, throwable: KotlinThrowable) {
        i(message: message, throwable: throwable)
    }
}
