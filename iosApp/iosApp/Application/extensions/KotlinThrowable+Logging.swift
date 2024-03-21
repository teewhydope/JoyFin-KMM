//
//  KotlinThrowable+Logging.swift
//  iosApp
//
//  Created by Animasahun Ibrahim on 14/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import IosData
import os.log

extension KotlinThrowable {
    var stackTrace: String {
        let iterator = getStackTrace().iterator()
        var stackTrace: String = ""
        while iterator.hasNext() {
            stackTrace.append(String(describing: iterator.next()))
            stackTrace.append("\n")
        }
        return stackTrace
    }
    
    func logError(useCase name: String) {
        os_log(
            "[ERROR] %@ :: Executing usecase `%@` (localized error: %@, cause: %@)",
            log: OSLog.useCase,
            type: .error,
            "\(#file)",
            name,
            String(describing: message),
            String(describing: cause)
        )

        os_log("%@", log: OSLog.useCase, type: .error, stackTrace)
    }

    func logError(useCase: IosData.BaseUseCase) {
        let useCaseName = String(describing: type(of: useCase))
        logError(useCase: useCaseName)
    }
}
