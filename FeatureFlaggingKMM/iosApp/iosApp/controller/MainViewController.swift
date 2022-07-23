//
//  MainViewController.swift
//  iosApp
//
//  Created by user222351 on 7/22/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import UIKit
import shared


@MainActor
class AsyncModel<Success>: ObservableObject {
    @Published var result: AsyncResult<Success> = .empty

    typealias AsyncOperation = () async throws -> Success

var operation : AsyncOperation

init(operation : @escaping AsyncOperation) {
    self.operation = operation
}

    func loadData() async {
        self.result = .inProgress

        do {
            self.result = .success( try await operation())
        } catch {
            self.result = .failure(error)
        }
    }
}

struct MainViewState {
    private var kmmSdk=HomeClass()

    func seasons() async throws -> [SeasonsResponse] {
        return try await kmmSdk.getAllSeasons()
    }
    
    func featureFlag() async throws -> Bool {
        return try await kmmSdk.getFeatureFlag() as! Bool
    }
}

func filterSeasons(name: String,seasons:[SeasonsResponse]) -> [SeasonPhoto] {
  
    return seasons.first { season in
        name==season.seasonName
    }?.contents ?? []
}


enum AsyncResult<Success> {
    case empty
    case inProgress
    case success(Success)
    case failure(Error)
}
